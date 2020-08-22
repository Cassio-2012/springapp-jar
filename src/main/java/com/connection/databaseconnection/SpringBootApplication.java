package com.connection.databaseconnection;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableFeignClients
public class SpringBootApplication implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET" , "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
	}
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/*");
	}

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(SpringBootApplication.class, args);
	}

}
