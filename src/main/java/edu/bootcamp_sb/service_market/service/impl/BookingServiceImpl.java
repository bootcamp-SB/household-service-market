package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.entity.BookingEntity;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import edu.bootcamp_sb.service_market.exception.client_exceptions.ClientHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.BookingRepository;
import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.repository.PaymentRepository;
import edu.bootcamp_sb.service_market.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl.entityToClientDto;
import static edu.bootcamp_sb.service_market.service.impl.PaymentServiceImpl.paymentEntityToPaymentDto;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final PaymentRepository paymentRepository;

    private final ClientRepository clientRepository;

    private final ObjectMapper mapper;







    @Override
    @PreAuthorize("hasAnyRole('admin','user','provider')")
    public ResponseEntity<BookingResponseDto> persist(BookingDto bookingDto) {

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setDate(LocalDate.parse(bookingDto.getDate()));
        bookingEntity.setStatus(bookingDto.getStatus());
        bookingEntity.setStartingTime(LocalTime.parse(bookingDto.getStartingTime()));
        bookingEntity.setEndingTime(LocalTime.parse(bookingDto.getEndingTime()));


        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setTimeStamp(LocalTime.now());
        paymentEntity.setAmount(bookingDto.getPayment().getAmount());
        paymentEntity.setDate(LocalDate.now());
        paymentEntity.setStatus(bookingEntity.getStatus());

        ClientEntity clientEntity = clientRepository.findById(bookingDto.getClientId()).orElseThrow(
                () -> new ClientHasBeenNotFoundException("NOT found")
        );

        bookingEntity.setClient(clientEntity);


        bookingEntity.setPayment(paymentRepository.save(paymentEntity));

        BookingEntity saved = bookingRepository.save(bookingEntity);

        ClientDto clientDto = entityToClientDto(saved.getClient());
        PaymentDto paymentDto = paymentEntityToPaymentDto(saved.getPayment());

        return ResponseEntity.ok(
                BookingResponseDto.builder()
                        .id(saved.getId())
                        .date(saved.getDate())
                        .status(saved.getStatus())
                        .endingTime(saved.getEndingTime())
                        .startingTime(saved.getStartingTime())
                        .payment(paymentDto)
                        .client(clientDto)
                        .build()
        );
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
}
