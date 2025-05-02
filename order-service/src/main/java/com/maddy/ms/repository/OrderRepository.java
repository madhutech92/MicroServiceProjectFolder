package com.maddy.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maddy.ms.dto.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
