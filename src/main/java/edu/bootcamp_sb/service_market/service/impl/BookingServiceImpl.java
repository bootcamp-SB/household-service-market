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


        ClientEntity clientEntity = clientRepository.findById(bookingDto.getClientId()).orElseThrow(
                () -> new ClientHasBeenNotFoundException("client is NOT found")
        );

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
    public ResponseEntity<Map<String, String>> cancelBooking(UUID id) {
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(() ->
                new BookingHasNotFoundException("No booking found"));
        bookingEntity.setStatus("canceled");
        return ResponseEntity.ok(Map.of(
                "cancellation", "successfully canceled booking called" + id));
    }
}
