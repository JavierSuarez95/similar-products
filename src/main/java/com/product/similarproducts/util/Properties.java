package com.product.similarproducts.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("similar-products")
@Getter @Setter
public class Properties {
 
    private String url;

}
