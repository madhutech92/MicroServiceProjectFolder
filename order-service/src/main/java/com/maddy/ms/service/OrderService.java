package com.maddy.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maddy.ms.controller.client.PaymentClient;
import com.maddy.ms.controller.client.UserClient;
import com.maddy.ms.dto.Order;
import com.maddy.ms.dto.Payment;
import com.maddy.ms.repository.OrderRepository;

import feign.FeignException;
import feign.FeignException.FeignClientException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentClient paymentClient;

	@Autowired
	private UserClient userClient;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@CircuitBreaker(name = "userServiceCB", fallbackMethod = "fallbackCreateOrder")
	@Retry(name = "userServiceRetry")
	public ResponseEntity<?> createOrder(Order order) {
		try {
			userClient.getUserById(order.getUserId());
		} catch (FeignException.NotFound ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found for the ID: " + order.getUserId());
		}

		Order savedOrder = orderRepository.save(order);

		Payment payment = new Payment();
		payment.setOrderId(savedOrder.getId());
		payment.setAmount(100.0);
		payment.setStatus("PENDING");

		paymentClient.createPayment(payment);
		return ResponseEntity.ok(savedOrder);
	}

	public List<Payment> getPaymentsByOrderId(Long orderId) {
		return paymentClient.getPaymentsByOrderId(orderId);
	}
	
	//fallback after retry
	public ResponseEntity<?> fallbackCreateOrder(Order order, Throwable e){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Unable to create Order now due to User Service Issue: "+e.getMessage());
		
	}

}
