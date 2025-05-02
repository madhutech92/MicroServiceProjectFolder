package com.maddy.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maddy.ms.dto.Order;
import com.maddy.ms.dto.Payment;
import com.maddy.ms.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
    	return orderService.createOrder(order);
    }
    
    @GetMapping("/{orderId}/payments")
    public List<Payment> getPaymentsByOrderId(@PathVariable Long orderId){
    	return orderService.getPaymentsByOrderId(orderId);
    }
    
}
