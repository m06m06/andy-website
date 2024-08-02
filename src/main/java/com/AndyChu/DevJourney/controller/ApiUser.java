package com.AndyChu.DevJourney.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AndyChu.DevJourney.model.User;
import com.AndyChu.DevJourney.model.UserRepository;

@RestController
public class ApiUser {
	
	private UserRepository userRepository;
	
	public ApiUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@PostMapping("/signUpUser")
	public ResponseEntity<User> signUpUser(@RequestBody User user){
		User savedUser = userRepository.save(user);
		return ResponseEntity.ok(savedUser);
	}
	
	@GetMapping("/userList")
	public List<User> showUserList() {	
		List<User> user = userRepository.findAll();       
		return user;
	}
	
	
}
