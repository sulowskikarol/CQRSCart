package com.sulowskikarol.cartservice.infrastructure.repository.jpa.mapper;

import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.model.CartItem;
import com.sulowskikarol.cartservice.infrastructure.repository.entity.CartEntity;
import com.sulowskikarol.cartservice.infrastructure.repository.entity.CartItemEmbeddable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartJpaMapper {

    Cart toDomain(CartEntity cartEntity);

    @Mapping(target = "version", ignore = true)
    CartEntity toEntity(Cart cart);

    @Mapping(target = "version", ignore = true)
    void updateEntity(@MappingTarget CartEntity cartEntity, Cart cart);

    CartItem toDomain(CartItemEmbeddable cartItemEmbeddable);

    CartItemEmbeddable toEntity(CartItem cartItem);

    List<CartItem> toDomainItems(List<CartItemEmbeddable> cartItemEmbeddableList);

    List<CartItemEmbeddable> toEntityItems(List<CartItem> cartItemList);
}
