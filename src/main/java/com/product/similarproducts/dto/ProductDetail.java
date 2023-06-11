package com.product.similarproducts.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ProductDetail {
	
	@NotNull
	@Size(min = 1)
	private String id;

	@NotNull
	@Size(min = 1)
	private String name;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Boolean availability;

}
