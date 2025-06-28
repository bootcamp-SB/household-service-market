package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.dto.reponse.PaymentResponseDto;
import edu.bootcamp_sb.service_market.entity.BookingEntity;
import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import edu.bootcamp_sb.service_market.repository.BookingRepository;
import edu.bootcamp_sb.service_market.repository.PaymentRepository;
import edu.bootcamp_sb.service_market.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;

    private final ObjectMapper mapper;


    @Override
    public ResponseEntity<PaymentResponseDto> make(PaymentDto paymentDto) {

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(paymentDto.getAmount());
        paymentEntity.setStatus(paymentDto.getStatus());
        paymentEntity.setTimeStamp(paymentDto.getTimeStamp());

        BookingEntity bookingEntity = bookingRepository.findById(
                paymentDto.getBookingId()).orElseThrow(
                () -> new RuntimeException("not Found"));

        paymentEntity.setBooking(bookingEntity);

        return ResponseEntity.ok(mapper.convertValue
                (paymentRepository.save(paymentEntity),
                        PaymentResponseDto.class));
    }

    @Override
    public ResponseEntity<List<PaymentResponseDto>> show() {
        Iterable<PaymentEntity> all = paymentRepository.findAll();
        ArrayList<PaymentResponseDto> paymentDtos = new ArrayList<>();

        all.forEach(entity->
                paymentDtos.add
                        (mapper.convertValue(entity, PaymentResponseDto.class))
        );

        return ResponseEntity.ok(paymentDtos);
    }

    @Override
    public ResponseEntity<PaymentResponseDto> byId(UUID id) {
        PaymentEntity byId
                = paymentRepository.findById(id).orElseThrow(
                        ()->new RuntimeException("ID not found"));

        return ResponseEntity.ok(
                PaymentResponseDto.builder()
                        .amount(byId.getAmount())
                        .booking(byId.getBooking())
                        .status(byId.getStatus())
                        .id(id).build()
        );
    }


}
