package com.AndyChu.DevJourney.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AndyChu.DevJourney.model.User;
import com.AndyChu.DevJourney.model.UserRepository;

@RestController
public class ApiUser {
	
	private UserRepository userRepository;
	
	@Autowired
    public ApiUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@GetMapping("/userList")
	public List<User> showUserList() {	
		List<User> user = userRepository.findAll();       
		return user;
	}
	
	
}
