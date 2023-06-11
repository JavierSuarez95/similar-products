package com.product.similarproducts.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class SimilarProductException extends Exception  {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus status;
	
	public SimilarProductException(EnumException exception) {
		super(exception.getMessage());
		this.status = exception.getStatus();
	}
	
	
}
