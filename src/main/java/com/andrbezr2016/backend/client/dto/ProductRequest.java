package com.andrbezr2016.backend.client.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
public class ProductRequest {

    @NotNull(message = "Name must not be null", groups = {Create.class})
    private String name;
    @NotNull(message = "Type must not be null", groups = {Create.class})
    private Product.ProductType type;
    private String description;
    @NotNull(message = "Author must not be null", groups = {Create.class})
    private UUID author;

    public interface Create {
    }
}
