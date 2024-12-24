package com.andrbezr2016.backend.client.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ProductResponse {

    private UUID id;
    private String name;
    private Product.ProductType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private TariffResponse tariff;
    private UUID author;
    private Long version;
}
