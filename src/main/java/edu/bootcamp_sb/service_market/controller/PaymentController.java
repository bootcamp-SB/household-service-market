package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto>make(@RequestBody PaymentDto payment){
        return paymentService.make(payment);

    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAll(){
        return paymentService.show();

    }
    @GetMapping("/id")
    public ResponseEntity<PaymentDto>getById(@RequestParam UUID id){
        return paymentService.byId(id);

    }
}
