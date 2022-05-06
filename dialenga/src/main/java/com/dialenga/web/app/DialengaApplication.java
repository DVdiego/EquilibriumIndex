package com.dialenga.web.app;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DialengaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DialengaApplication.class, args);
		Logger.getGlobal().info("Start DialengaApplication");
	}

}
