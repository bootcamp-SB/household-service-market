package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    ResponseEntity<BookingDto> persist(BookingDto bookingDto);

    ResponseEntity<List<BookingDto>>show();

}
