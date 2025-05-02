package com.maddy.ms.controller.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.maddy.ms.dto.Payment;

@FeignClient(name = "payment-service")
public interface PaymentClient {

	@PostMapping("/payments")
	Payment createPayment(@RequestBody Payment payment);

	@GetMapping("/payments/order/{orderId}")
	List<Payment> getPaymentsByOrderId(@PathVariable Long orderId);
	
}
