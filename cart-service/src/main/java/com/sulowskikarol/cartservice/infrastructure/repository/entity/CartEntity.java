package com.sulowskikarol.cartservice.infrastructure.repository.entity;

import com.sulowskikarol.cartservice.domain.model.enums.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class CartEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    private List<CartItemEmbeddable> items = new ArrayList<>();

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

}
