package com.pramati.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("com.pramati.cache")
@Configuration
@PropertySource("classpath:application.properties")
public class CacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

}
