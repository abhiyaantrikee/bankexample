package com.bank.eventhandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EventHandlerServiceApplication {
	
	@Autowired
	MessageListener messageListener;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EventHandlerServiceApplication.class, args);
	}
}
