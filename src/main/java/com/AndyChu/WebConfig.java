package com.AndyChu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

	 private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
	 
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	logger.info("Configuring CORS mappings..."); // Log before configuration
                registry.addMapping("/**") // 匹配所有的URL
                        .allowedOrigins("http://localhost:8081") // 允許的來源
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD") // 允許的方法
                        .allowedHeaders("*") // 允許的標頭
                        .allowCredentials(true); // 允許憑證
                logger.info("CORS mappings configured successfully.");
            }
        };
    }
}
