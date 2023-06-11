package com.product.similarproducts.util;

import java.io.IOException;

import org.apache.http.client.fluent.Request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiRestCall {

	private ApiRestCall() {}

	public static String getRequest(String url, int timeout) {
		
		String response = null;
		
		try {
			return Request.Get(url).socketTimeout(timeout).execute().returnContent().asString();
		} catch (IOException e) {
			log.error(e.getMessage().concat(" ").concat(url));
		}

		return response;

	}
	
}
