package com.example.demo;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@SpringBootApplication
public class HashcodeApplication {
	public static void main(String[] args) {
		Application.launch(FileHashClient.class, args);
	}
}
