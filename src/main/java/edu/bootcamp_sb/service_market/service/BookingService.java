package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingWithPaymentNClientResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    ResponseEntity<BookingWithPaymentNClientResponseDto> persist(BookingDto bookingDto);

    ResponseEntity<List<BookingDto>>show();

}
