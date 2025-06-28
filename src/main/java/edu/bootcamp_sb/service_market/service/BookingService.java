package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    ResponseEntity<BookingResponseDto> persist(BookingDto bookingDto);

    ResponseEntity<List<BookingDto>>show();

}
