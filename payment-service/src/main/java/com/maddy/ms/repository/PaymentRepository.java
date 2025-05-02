package com.maddy.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maddy.ms.dto.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	
	List<Payment> findByOrderId(Long orderId);
}
