package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto>create(@RequestBody BookingDto booking){
        return bookingService.persist(booking);
    }


}
