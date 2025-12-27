package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import edu.bootcamp_sb.service_market.repository.PaymentRepository;
import edu.bootcamp_sb.service_market.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ObjectMapper mapper;

    public  static PaymentDto paymentEntityToPaymentDto(PaymentEntity preEntity){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(preEntity.getId());
        paymentDto.setStatus(preEntity.getStatus());
        paymentDto.setAmount(preEntity.getAmount());
        return paymentDto;
    }


    @Override
    @PreAuthorize("hasAnyRole('admin','user')")
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
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<PaymentDto>> show() {
        Iterable<PaymentEntity> all = paymentRepository.findAll();
        ArrayList<PaymentDto> paymentDtoList = new ArrayList<>();

        all.forEach(entity->
                paymentDtoList.add
                        (mapper.convertValue(entity, PaymentDto.class))
        );
        return ResponseEntity.ok(paymentDtoList);
    }

    @Override
    @PreAuthorize("hasAnyRole('admin,user')")
    public ResponseEntity<PaymentDto> byId(UUID id) {
        PaymentEntity byId
                = paymentRepository.findById(id).orElseThrow(
                        ()->new RuntimeException("ID not found"));

        return ResponseEntity.ok(mapper.convertValue(byId,PaymentDto.class));
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteById(UUID id) {
        Optional<PaymentEntity> paymentEntity = paymentRepository.findById(id);
        if(paymentEntity.isPresent()){
            paymentRepository.deleteById(id);
        }else{
            throw new RuntimeException("payment not found");
        }
        return ResponseEntity.ok(Map.of("deletion","sucessful-> "+id));
    }


}
