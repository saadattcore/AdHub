package com.adhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.adhub.controller")
public class WebConfig {

	@Bean
	public RestClient.Builder restClientBuilder() {
		return RestClient.builder();
	}
}
