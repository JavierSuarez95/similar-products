package com.product.similarproducts.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.product.similarproducts.dto.ProductDetail;
import com.product.similarproducts.dto.SimilarProducts;
import com.product.similarproducts.exception.EnumException;
import com.product.similarproducts.exception.SimilarProductException;
import com.product.similarproducts.util.ApiRestCall;
import com.product.similarproducts.util.Properties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class SimilarProductService {

	private Validator validator;
	private Properties properties;

	public SimilarProducts getProductSimilar(String productId) throws SimilarProductException {

		List<String> idProductsSimilars = this.getProductSimilarIds(productId);

		if (idProductsSimilars == null || idProductsSimilars.isEmpty()) {
			throw new SimilarProductException(EnumException.PRODUCT_NOT_FOUND);
		}

		List<ProductDetail> productDetails = idProductsSimilars.stream().map(this::getProductById)
				.collect(Collectors.toList());

		return this.getSimilarProductsValid(productDetails);

	}

	private List<String> getProductSimilarIds(String productId) {

		Gson gson = new Gson();

		String response = ApiRestCall.getRequest(properties.getUrl() + productId + "/similarids", 1000);

		return gson.fromJson(response, new TypeToken<List<String>>() {
		}.getType());

	}

	private ProductDetail getProductById(String productId) {

		Gson gson = new Gson();

		String response = ApiRestCall.getRequest(properties.getUrl() + productId, 1000);

		ProductDetail productDetail = gson.fromJson(response, new TypeToken<ProductDetail>() {
		}.getType());

		if (productDetail != null) {
			Set<ConstraintViolation<ProductDetail>> violations = validator.validate(productDetail);
			if (!violations.isEmpty()) {
				log.error(violations.toString());
				return null;
			}
		}

		return productDetail;

	}

	private SimilarProducts getSimilarProductsValid(List<ProductDetail> productDetails) throws SimilarProductException {

		SimilarProducts similarProducts = new SimilarProducts();
		productDetails.removeIf(Objects::isNull);
		similarProducts.addAll(productDetails);

		if (similarProducts.isEmpty()) {
			throw new SimilarProductException(EnumException.PRODUCT_NOT_FOUND);
		}

		return similarProducts;

	}

}
