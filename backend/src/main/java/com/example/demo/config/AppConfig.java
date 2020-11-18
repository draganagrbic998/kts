package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableAsync
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurationSupport {
	
	//omogucujemo asinhrono slanje mejla i transakcije
	//pamptim da je profesor imao ovakvu klasu u svojim primerima, pa reko da 
	//i mi uradimo ovako
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}


}
