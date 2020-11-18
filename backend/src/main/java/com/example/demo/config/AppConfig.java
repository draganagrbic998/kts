package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableTransactionManagement
public class AppConfig {
	
	//omogucujemo asinhrono slanje mejla i transakcije
	//pamptim da je profesor imao ovakvu klasu u svojim primerima, pa reko da 
	//i mi uradimo ovako

}
