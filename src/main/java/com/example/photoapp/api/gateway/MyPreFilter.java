package com.example.photoapp.api.gateway;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class MyPreFilter implements GlobalFilter {

	final Logger logger = LoggerFactory.getLogger(MyPreFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		logger.info("my very first pre-filter is executed...");

		String requestPath = exchange.getRequest().getPath().toString();

		logger.info("Request path = " + requestPath);

		HttpHeaders headers = exchange.getRequest().getHeaders();

		Set<String> headerNames = headers.keySet();

		headerNames.forEach((headerName) -> {
			String headervalue = headers.getFirst(headerName);
			logger.info(headerName + " " + headervalue);
		});

		return chain.filter(exchange);
	}

}
