package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.entity.BookingEntity;
import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import edu.bootcamp_sb.service_market.repository.BookingRepository;
import edu.bootcamp_sb.service_market.repository.PaymentRepository;
import edu.bootcamp_sb.service_market.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final PaymentRepository paymentRepository;

    private final ObjectMapper mapper;


    @Override
    public ResponseEntity<BookingResponseDto> persist(BookingDto bookingDto) {

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setDate(bookingDto.getDate());
        bookingEntity.setStatus(bookingDto.getStatus());
        bookingEntity.setTimestamp(bookingDto.getTimestamp());
        bookingEntity.setDate(bookingDto.getDate());


        return ResponseEntity.ok(
                mapper.convertValue(
                bookingRepository.save(bookingEntity),
                BookingResponseDto.class
        ));
    }

    @Override
    public ResponseEntity<List<BookingDto>> show() {

        return null;
    }
}
