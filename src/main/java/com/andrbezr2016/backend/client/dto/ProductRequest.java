package com.andrbezr2016.backend.client.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ProductRequest {

    @NotNull(message = "Name must not be null")
    private String name;
    @NotNull(message = "Type must not be null")
    private Product.ProductType type;
    private String description;
    @NotNull(message = "Author must not be null")
    private UUID author;
}
