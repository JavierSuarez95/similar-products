package com.product.similarproducts.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumException {

	PRODUCT_NOT_FOUND("Product Not found", HttpStatus.NOT_FOUND);
	
	private final String message;
	private final HttpStatus status;
	
	
}
