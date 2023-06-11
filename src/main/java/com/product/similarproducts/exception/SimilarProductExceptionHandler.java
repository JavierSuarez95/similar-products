package com.product.similarproducts.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class SimilarProductExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { SimilarProductException.class })
	public ResponseEntity<String> handleSimilarProductException(SimilarProductException spe) {

		log.error(spe.getMessage());

		return new ResponseEntity<>(spe.getMessage(), spe.getStatus());
	}

}
