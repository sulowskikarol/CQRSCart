package com.sulowskikarol.cartservice.infrastructure.repository.jpa.mapper;

import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.model.CartItem;
import com.sulowskikarol.cartservice.infrastructure.repository.entity.CartEntity;
import com.sulowskikarol.cartservice.infrastructure.repository.entity.CartItemEmbeddable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartJpaMapper {

    Cart toDomain(CartEntity cartEntity);

    CartEntity toEntity(Cart cart);

    CartItem toDomain(CartItemEmbeddable cartItemEmbeddable);

    CartItemEmbeddable toEntity(CartItem cartItem);

    List<CartItem> toDomainItems(List<CartItemEmbeddable> cartItemEmbeddableList);

    List<CartItemEmbeddable> toEntityItems(List<CartItem> cartItemList);
}
