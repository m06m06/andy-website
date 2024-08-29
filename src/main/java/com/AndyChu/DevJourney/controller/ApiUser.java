package com.AndyChu.DevJourney.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	private final UserRepository userRepository;
	
	public ApiUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@PostMapping("/signUpUser")
	@Operation(summary = "註冊新用戶")
	public ResponseEntity<?> signUpUser(@RequestBody User user){
		// 註冊資料檢核
        ValidationResult validationResult = UserValidation.validateUser(user, userRepository);
        if (validationResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResult.getValidatorMsg());
        }
        
        // 儲存註冊資料
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@GetMapping("/userList")
	@Operation(summary  = "用戶列表")
	public List<User> showUserList() {	
		List<User> user = userRepository.findAll();       
		return user;
	}
	
	
}
