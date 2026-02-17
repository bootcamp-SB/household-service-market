package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.reponse.BookingResponseDto;
import edu.bootcamp_sb.service_market.dto.request.BookingRequestDto;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BookingService {

    ResponseEntity<BookingResponseDto> persist(BookingRequestDto bookingDto) throws MessagingException;

    ResponseEntity<List<BookingDto>>show();

    ResponseEntity<BookingResponseDto>getBookingById(UUID id);

    ResponseEntity<Map<String ,String>> cancelBooking(UUID id) throws MessagingException;

    ResponseEntity<List<BookingResponseDto>> getAllWithUserId(String id);

    ResponseEntity<Map<String,Integer>>getBookingCountOfAUser(String userid);

    ResponseEntity<Map<String,String>>rescheduleBooking(
            String userId, LocalDate rescheduleDate ,LocalTime rescheduleTime);

    ResponseEntity<Map<String,String>>updateStatusAsComplete(String userId);



}
