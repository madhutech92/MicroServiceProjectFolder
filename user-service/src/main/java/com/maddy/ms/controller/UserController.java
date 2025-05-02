package com.maddy.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maddy.ms.dto.User;
import com.maddy.ms.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping
	public User saveUser(@RequestBody User entity) {
		return userRepository.saveAndFlush(entity);
	}
	
	@PostMapping("/saveall")
	public List<User> saveUser(@RequestBody List<User> entities) {
		return userRepository.saveAllAndFlush(entities);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		return userRepository.findById(userId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
}
