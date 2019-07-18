package com.pramati.kvstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.pramati.kvstore")
@EnableAsync
@EnableScheduling
public class KvStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KvStoreApplication.class, args);
	}

}
