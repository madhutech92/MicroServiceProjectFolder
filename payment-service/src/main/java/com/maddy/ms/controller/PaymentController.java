package com.maddy.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maddy.ms.dto.Payment;
import com.maddy.ms.repository.PaymentRepository;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping
	public Payment createPayment(@RequestBody Payment payment) {
		return paymentRepository.save(payment);
	}

	@GetMapping("/order/{orderId}")
	public List<Payment> getPaymentsByOrderId(@PathVariable Long orderId) {
		return paymentRepository.findByOrderId(orderId);
	}

	@GetMapping
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
}
