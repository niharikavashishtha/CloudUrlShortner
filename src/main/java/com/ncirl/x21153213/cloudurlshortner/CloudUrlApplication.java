package com.ncirl.x21153213.cloudurlshortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CloudUrlApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudUrlApplication.class, args);
	}
}
