package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.PaymentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    ResponseEntity<PaymentDto> make(PaymentDto paymentDto);

    ResponseEntity<List<PaymentDto>> show();

    ResponseEntity<PaymentDto> byId(UUID id);
}
