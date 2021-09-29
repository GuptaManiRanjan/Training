package com.mani.springboot.thymeleafdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafdemoApplication {
	
	private static final Logger LOGGER = LogManager.getLogger(ThymeleafdemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafdemoApplication.class, args);
		LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.error("Error level log message");
	}

}
