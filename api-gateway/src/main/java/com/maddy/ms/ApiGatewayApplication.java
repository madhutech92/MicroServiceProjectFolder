package com.maddy.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	/*
	 * @Bean KeyResolver userKeyResolver() { return exchange -> { String userId =
	 * exchange.getRequest() .getHeaders() .getFirst("X-User-Id"); return
	 * Mono.just(userId != null ? userId : "anonymous"); }; }
	 */
	
	//If user is authenticated via JWT or Spring Security Principal:
	/*
	 * @Bean KeyResolver userKeyResolver() { return exchange ->
	 * exchange.getPrincipal() .map(principal -> principal.getName())
	 * .defaultIfEmpty("anonymous"); }
	 */
}
