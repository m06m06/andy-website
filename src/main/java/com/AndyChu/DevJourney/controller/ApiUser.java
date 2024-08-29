package com.AndyChu.DevJourney.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AndyChu.DevJourney.model.User;
import com.AndyChu.DevJourney.model.UserRepository;
import com.AndyChu.DevJourney.model.UserValidation;
import com.AndyChu.DevJourney.util.ValidationResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping("api")
@Tag(name = "User Management System")
public class ApiUser {
	private static final Logger logger = LoggerFactory.getLogger(ApiUser.class);
	
	private final UserRepository userRepository;
	
	public ApiUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@PostMapping("/signUpUser")
	@Operation(summary = "註冊新用戶")
	public ResponseEntity<?> signUpUser(@RequestBody User user){
		logger.info("User registration attempt for account: {}", user.getAccount());
		
		// 註冊資料檢核
        ValidationResult validationResult = UserValidation.validateUser(user, userRepository);
        logger.warn("User registration failed for account: {}", user.getAccount());
        if (validationResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResult.getValidatorMsg());
        }
        
        // 儲存註冊資料
        User savedUser = userRepository.save(user);
        logger.info("User successfully registered with account: {}", savedUser.getAccount());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@GetMapping("/userList")
	@Operation(summary  = "用戶列表")
	public List<User> showUserList() {	
		logger.info("Fetching user list");
		List<User> user = userRepository.findAll();       
		return user;
	}
	
	
}
