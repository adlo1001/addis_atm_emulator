package com.addisuthomas.atm_service.config;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		SessionHolder instance = SessionHolder.getInstance();
		if (Objects.nonNull(instance.getToken()))
			request.getHeaders().add("Authorization", instance.getToken());
		logger.info("RRRRRRRRRRRRRRRR"+instance.getToken());
		return execution.execute(request, body);
	}
}