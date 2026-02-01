package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.dto.request.BookingRequestDto;
import edu.bootcamp_sb.service_market.service.BookingService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('admin','user','provider')")
    public ResponseEntity<BookingResponseDto>create(@RequestBody BookingRequestDto booking) throws MessagingException {
        return bookingService.persist(booking);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<BookingDto>>getAll(){
        return bookingService.show();
    }

    @GetMapping("/by-id")
    public ResponseEntity<BookingResponseDto>getById(@RequestParam UUID id){
        return  bookingService.getBookingById(id);
    }
    @PutMapping("/cancel")
    public ResponseEntity<Map<String,String>>cancelBooking(UUID id){
        return bookingService.cancelBooking(id);
    }

    @GetMapping("/by-client-id")
    public ResponseEntity<List<BookingResponseDto>>getAllByClientId(@RequestParam String id){
        return  bookingService.getAllWithUserId(id);
    }
}
