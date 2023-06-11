package com.product.similarproducts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.product.similarproducts.dto.SimilarProducts;
import com.product.similarproducts.exception.SimilarProductException;
import com.product.similarproducts.service.SimilarProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SimilarProductController {

	private SimilarProductService similarProductService;
	
	@GetMapping("/product/{productId}/similar")
	public SimilarProducts getProductSimilar(@PathVariable(name = "productId", required = true) String productId) throws SimilarProductException {
		return similarProductService.getProductSimilar(productId);
	}
}
