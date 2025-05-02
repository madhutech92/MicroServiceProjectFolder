package com.maddy.ms.controller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.maddy.ms.dto.User;

@FeignClient(name = "user-service")
public interface UserClient {

	@GetMapping("/users/{userId}")
	User getUserById(@PathVariable Long userId);
}
