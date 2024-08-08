package com.AndyChu.DevJourney.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {

	 @Bean
	    public GroupedOpenApi publicApi() {
	        return GroupedOpenApi.builder()
	                .group("Public API")
	                .packagesToScan("com.AndyChu.DevJourney.controller")
	                .build();
	    }
}
