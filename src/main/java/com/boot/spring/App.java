package com.boot.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * App entrance!
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	//Why I don't Override this, the war file runs well too ???
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}
}