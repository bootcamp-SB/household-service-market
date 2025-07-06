package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import edu.bootcamp_sb.service_market.repository.BookingRepository;
import edu.bootcamp_sb.service_market.repository.PaymentRepository;
import edu.bootcamp_sb.service_market.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ObjectMapper mapper;


    @Override
    public ResponseEntity<PaymentDto> make(PaymentDto paymentDto) {

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(paymentDto.getAmount());
        paymentEntity.setStatus(paymentDto.getStatus());
        paymentEntity.setTimeStamp(LocalTime.now());
        paymentEntity.setDate(LocalDate.now());



        return ResponseEntity.ok(mapper.convertValue
                (paymentRepository.save(paymentEntity),
                        PaymentDto.class));
    }

    @Override
    public ResponseEntity<List<PaymentDto>> show() {
        Iterable<PaymentEntity> all = paymentRepository.findAll();
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        all.forEach(entity->
                paymentDtos.add
                        (mapper.convertValue(entity, PaymentDto.class))
        );
        return ResponseEntity.ok(paymentDtos);
    }

    @Override
    public ResponseEntity<PaymentDto> byId(UUID id) {
        PaymentEntity byId
                = paymentRepository.findById(id).orElseThrow(
                        ()->new RuntimeException("ID not found"));

        return ResponseEntity.ok(mapper.convertValue(byId,PaymentDto.class));
    }


}
