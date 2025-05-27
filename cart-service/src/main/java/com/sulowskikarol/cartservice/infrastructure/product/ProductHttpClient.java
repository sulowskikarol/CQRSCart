package com.sulowskikarol.cartservice.infrastructure.product;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ProductHttpClient {
    Optional<BigDecimal> getPriceByProductId(UUID productId);
}
