package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.PaymentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {

    ResponseEntity<PaymentDto>make(PaymentDto paymentDto);

    ResponseEntity<List<PaymentDto>>show();
}
