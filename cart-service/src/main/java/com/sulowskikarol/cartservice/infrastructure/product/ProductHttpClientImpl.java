package com.sulowskikarol.cartservice.infrastructure.product;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductHttpClientImpl implements ProductHttpClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ProductHttpClientImpl(RestTemplate restTemplate, ProductServiceProperties productServiceProperties) {
        this.restTemplate = restTemplate;
        this.baseUrl = productServiceProperties.getBaseUrl();
    }

    @Override
    public Optional<BigDecimal> getPriceByProductId(UUID productId) {

        try {
            ProductResponse productResponse = restTemplate.getForObject(
                    baseUrl + "/product/" + productId + "/price",
                    ProductResponse.class
            );

            if (productResponse == null || productResponse.price() == null) {
                return Optional.empty();
            }

            return Optional.of(productResponse.price());
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
