package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.entity.BookingEntity;
import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import edu.bootcamp_sb.service_market.repository.BookingRepository;
import edu.bootcamp_sb.service_market.repository.PaymentRepository;
import edu.bootcamp_sb.service_market.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final PaymentRepository paymentRepository;

    private final ObjectMapper mapper;


    @Override
    public ResponseEntity<BookingDto> persist(BookingDto bookingDto) {

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

        bookingEntity.setPayment(paymentRepository.save(paymentEntity));

        return ResponseEntity.ok(
                mapper.convertValue(
                        bookingRepository.save(bookingEntity),
                BookingDto.class
        ));
    }

    @Override
    public ResponseEntity<List<BookingDto>> show() {
        Iterable<BookingEntity> bookingEntities = bookingRepository.findAll();
        ArrayList<BookingDto> bookingDtos = new ArrayList<>();

        
        bookingEntities.forEach(bookingEntity ->
                    bookingDtos.add(
                            mapper.convertValue(
                                    bookingEntity, BookingDto.class
                            )));

        return ResponseEntity.ok(bookingDtos);
    }
}
