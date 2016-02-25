package com.sylvaingoutouly.spring.websocket;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
@EnableAsync
public class Application {

	@Bean public Filter etagFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
