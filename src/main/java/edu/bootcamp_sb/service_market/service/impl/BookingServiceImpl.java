package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.dto.request.BookingRequestDto;
import edu.bootcamp_sb.service_market.entity.BookingEntity;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.entity.ServiceGigEntity;
import edu.bootcamp_sb.service_market.exception.booking_exception.BookingHasNotFoundException;
import edu.bootcamp_sb.service_market.exception.client_exceptions.ClientHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderGigHasNotFound;
import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.*;
import edu.bootcamp_sb.service_market.service.BookingService;
import edu.bootcamp_sb.service_market.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl.clientEntityToClientResponseDto;
import static edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl.entityToClientDto;
import static edu.bootcamp_sb.service_market.service.impl.ProviderServiceImpl.convertProviderEntityToProviderDto;
import static edu.bootcamp_sb.service_market.service.impl.ServiceGigServiceImpl.convertGigEntityToGigResponseEntity;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final ClientRepository clientRepository;

    private final ProviderRepository providerRepository;

    private final ServiceGigRepository gigRepository;

    private final EmailService emailService;

    private final ObjectMapper mapper;




    public static BookingResponseDto bookingEntityToBookingResponseDto(
            BookingEntity bookingEntity)
    {
        return BookingResponseDto.builder()
                .id(bookingEntity.getId())
                .name(bookingEntity.getName())
                .address(bookingEntity.getAddress())
                .additionalInformation(bookingEntity.getAdditionalInformation())
                .contactNo(bookingEntity.getContactNo())
                .email(bookingEntity.getEmail())
                .status(bookingEntity.getStatus())
                .startingDate(bookingEntity.getStartingDate())
                .startingTime(bookingEntity.getStartingTime())
                .serviceGigResponseDto(
                        convertGigEntityToGigResponseEntity(bookingEntity.getGigEntity())
                )
                .providerDto(convertProviderEntityToProviderDto(bookingEntity.getServiceProvider()))
                .clientDto(clientEntityToClientResponseDto(bookingEntity.getClient()))
                .build();
    }




    @Override
    @PreAuthorize("hasAnyRole('admin','user','provider')")
    public ResponseEntity<BookingResponseDto> persist(BookingRequestDto bookingDto) throws MessagingException {

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setStartingDate(bookingDto.getStartingDate());
        bookingEntity.setStatus(bookingDto.getStatus());
        bookingEntity.setStartingTime(LocalTime.parse(bookingDto.getStartingTime()));
        bookingEntity.setAdditionalInformation(bookingDto.getAdditionalInformation());

        if(bookingDto.getName() != null){
            bookingEntity.setName(bookingDto.getName());
        }

        if(bookingDto.getEmail() != null){
            bookingEntity.setEmail(bookingDto.getEmail());

        }

        if(bookingDto.getContactNo() !=null){
            bookingEntity.setContactNo(bookingDto.getContactNo());
        }

        if(bookingDto.getAddress()!=null){
            bookingEntity.setAddress(bookingDto.getAddress());
        }

        ClientEntity clientEntity = clientRepository.findById(bookingDto.getClientId()).orElseThrow(
                () -> new ClientHasBeenNotFoundException("client is NOT found")
        );

        if(bookingDto.getName() == null || bookingDto.getName().isEmpty() ){
            bookingEntity.setName(clientEntity.getFirstName()+ " " + clientEntity.getLastName());
        }

        if(bookingDto.getEmail() == null || bookingDto.getEmail().isEmpty()){
            bookingEntity.setEmail(clientEntity.getEmail());
        }

        if(bookingDto.getAddress() == null || bookingDto.getAddress().isEmpty()){
            bookingEntity.setAddress(clientEntity.getAddress());
        }

        bookingEntity.setClient(clientEntity);

        ProviderEntity provider = providerRepository.findById(bookingDto.getProviderId()).orElseThrow(() ->
                new ProviderHasBeenNotFoundException("no provider"));

        bookingEntity.setServiceProvider(provider);

        ServiceGigEntity serviceGigEntity = gigRepository.findById(bookingDto.getGigId()).orElseThrow(() -> new
                ProviderGigHasNotFound("No gig found with" + bookingDto.getGigId()));

        bookingEntity.setGigEntity(serviceGigEntity);

        BookingEntity saved = bookingRepository.save(bookingEntity);

        HashMap<String, Object> emailVariables = new HashMap<>();
        emailVariables.put("username",saved.getClient().getUsername());
        emailVariables.put("providerUsername",saved.getServiceProvider().getUserName());
        emailVariables.put("bookingId",saved.getId());
        emailVariables.put("address",saved.getClient().getAddress());
        emailVariables.put("service",saved.getGigEntity().getCategory().getName());
        emailVariables.put("basePrice",saved.getGigEntity().getBasePrice());
        emailVariables.put("bookingDate",saved.getStartingDate());
        emailVariables.put("bookingTime",saved.getStartingTime());

        emailService.sendBookingConfirmationMail(saved.getClient().getEmail(),emailVariables);


        return ResponseEntity.ok(bookingEntityToBookingResponseDto(saved));
    }

    @Override
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<BookingDto>> show() {
        Iterable<BookingEntity> bookingEntities = bookingRepository.findAll();
        ArrayList<BookingDto> bookingDtoList = new ArrayList<>();

        
        bookingEntities.forEach(bookingEntity ->
                    bookingDtoList.add(
                            mapper.convertValue(
                                    bookingEntity, BookingDto.class
                            )));

        return ResponseEntity.ok(bookingDtoList);
    }

    @Override
    public ResponseEntity<BookingResponseDto> getBookingById(UUID id) {
       BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(
                ()-> new BookingHasNotFoundException("no booking with id called "+id));

        return ResponseEntity.ok(bookingEntityToBookingResponseDto(bookingEntity));
    }

    @Override
    public ResponseEntity<Map<String, String>> cancelBooking(UUID id) throws MessagingException {
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(() ->
                new BookingHasNotFoundException("No booking found"));

        HashMap<String, Object> cancellationEmailVariables = new HashMap<>();
        cancellationEmailVariables.put("username",bookingEntity.getClient().getUsername());
        cancellationEmailVariables.put("bookingId",bookingEntity.getId());
        cancellationEmailVariables.put("providerUsername",
                bookingEntity.getServiceProvider().getUserName());
        cancellationEmailVariables.put("scheduledDate", bookingEntity.getStartingDate());
        cancellationEmailVariables.put("scheduledTime",bookingEntity.getStartingTime());
        cancellationEmailVariables.put("serviceType",
                bookingEntity.getGigEntity().getCategory().getName());

        emailService.sendBookingCanceledEmailToUser(bookingEntity.getEmail(),cancellationEmailVariables);



        bookingEntity.setStatus("canceled");
        return ResponseEntity.ok(Map.of(
                "cancellation", "successfully canceled booking called" + id));
    }

    @Override
    public ResponseEntity<List<BookingResponseDto>> getAllWithUserId(String id) {
        Iterable<BookingEntity> allByClientId =
                bookingRepository.findAllByClientId(UUID.fromString(id));

        ArrayList<BookingResponseDto> bookingResponseDtoList = new ArrayList<>();

        allByClientId.forEach(bookingEntity ->
                bookingResponseDtoList.add(bookingEntityToBookingResponseDto(bookingEntity)));

        return ResponseEntity.ok(bookingResponseDtoList);
    }

    @Override
    public ResponseEntity<Map<String, Integer>> getBookingCountOfAUser(String userid) {
        Integer countByClientId = bookingRepository.countByClientId(UUID.fromString(userid));
        return ResponseEntity.ok(Map.of("booking count",countByClientId));
    }

    @Override
    public ResponseEntity<Map<String, String>> rescheduleBooking(
            String userId, LocalDate rescheduleDate , LocalTime rescheduleTime) {

        BookingEntity bookingEntity = bookingRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new BookingHasNotFoundException("booking is not found"));

        bookingEntity.setStatus("pending");
        bookingEntity.setStartingDate(rescheduleDate);
        bookingEntity.setStartingTime(rescheduleTime);
        bookingRepository.save(bookingEntity);
        return ResponseEntity.ok(Map.of("Success","Booking is reschduled"));
    }

    @Override
    public ResponseEntity<Map<String, String>> updateStatusAsComplete(String userId) {

        BookingEntity bookingEntity = bookingRepository.findById(UUID.fromString(userId))
                .orElseThrow(() ->
                        new BookingHasNotFoundException(
                                "Booking has not found with id " + userId));
        bookingEntity.setStatus("completed");
        bookingRepository.save(bookingEntity);
        return ResponseEntity.ok(Map.of("Success","Marked as completed"));
    }

    @Override
    public ResponseEntity<List<BookingResponseDto>> getAllByStatusAndClientId(
            String userId, String status) {

        ArrayList<BookingResponseDto> bookingResponseDtos = new ArrayList<>();

        Iterable<BookingEntity> bookingEntities =
                bookingRepository.findByClientIdAndStatus(UUID.fromString(userId), status);

        bookingEntities.forEach(entity->
            bookingResponseDtos.add(bookingEntityToBookingResponseDto(entity))
        );

        return ResponseEntity.ok(bookingResponseDtos);
    }
}
