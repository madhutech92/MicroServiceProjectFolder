package com.maddy.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maddy.ms.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
