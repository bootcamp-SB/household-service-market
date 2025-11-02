package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('admin','user','provider')")
    public ResponseEntity<BookingResponseDto>create(@RequestBody BookingDto booking){

        return bookingService.persist(booking);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<BookingDto>>getAll(){
        return bookingService.show();
    }


}
