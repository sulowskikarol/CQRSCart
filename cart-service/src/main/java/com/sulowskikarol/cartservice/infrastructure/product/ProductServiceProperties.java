package com.sulowskikarol.cartservice.infrastructure.product;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "product-service")
@Getter
@Setter
public class ProductServiceProperties {

    private String baseUrl;
}
