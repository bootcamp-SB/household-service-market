package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apu/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto>make(@RequestBody PaymentDto payment){
        return paymentService.make(payment);

    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>>getAll(){
        return paymentService.show();

    }
}
