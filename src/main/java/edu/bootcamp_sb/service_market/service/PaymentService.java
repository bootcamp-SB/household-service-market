package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.dto.reponse.PaymentResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    ResponseEntity<PaymentResponseDto> make(PaymentDto paymentDto);

    ResponseEntity<List<PaymentResponseDto>> show();

    ResponseEntity<PaymentResponseDto> byId(UUID id);
}
