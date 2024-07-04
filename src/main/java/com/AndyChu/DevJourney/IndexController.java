package com.AndyChu.DevJourney;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
		@GetMapping
		public String hello(){
			return "你好這裡是Andy軟體工程師的成長記錄";  
		}
}
