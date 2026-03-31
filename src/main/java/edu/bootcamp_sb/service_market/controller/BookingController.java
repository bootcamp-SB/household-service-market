package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.dto.request.BookingRequestDto;
import edu.bootcamp_sb.service_market.service.BookingService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public ResponseEntity<BookingResponseDto>create(
            @AuthenticationPrincipal Jwt token,@RequestBody BookingRequestDto booking
    ) throws MessagingException {
        String userId = token.getSubject();
        return bookingService.persist(userId,booking);
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
    public ResponseEntity<Map<String,String>>cancelBooking(@RequestParam UUID bookingId) throws MessagingException{
        return bookingService.cancelBooking(bookingId);
    }

    @GetMapping("/by-client-id")
    public ResponseEntity<List<BookingResponseDto>>getAllByClientId(@AuthenticationPrincipal Jwt jwt){
       String userId = jwt.getSubject();
        return  bookingService.getAllWithUserId(userId);
    }

    @GetMapping("/user/booking-count")
    public ResponseEntity<Map<String,Integer>>getBookingCountOfAUser(@AuthenticationPrincipal Jwt token){
        String userId = token.getSubject();
        return bookingService.getBookingCountOfAUser(userId);
    }

    @PutMapping("/reschedule")
    public ResponseEntity<Map<String,String>>rescheduleBooking(
            @RequestParam String id, @RequestParam LocalDate rescheduleDate,
            @RequestParam LocalTime rescheduleTime){
        return bookingService.rescheduleBooking(id, rescheduleDate, rescheduleTime);
    }

    @PatchMapping("/completed")
    public ResponseEntity<Map<String,String>>updateBookingToCompleted(@RequestParam String id){
        return bookingService.updateStatusAsComplete(id);
    }

    @GetMapping("/user/by-status")
    public ResponseEntity<List<BookingResponseDto>>getAllByClientIdAndStatus(
            @AuthenticationPrincipal Jwt token, @RequestParam String status){

        String userId = token.getSubject();

        return bookingService.getAllByStatusAndClientId(userId,status);
    }



}
