package com.andrbezr2016.backend.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
public class ProductResponse {

    private UUID id;
    private String name;
    private ProductType type;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String description;
    private TariffResponse tariff;
    private UUID author;
    private Long version;

    public enum ProductType {
        LOAN, CARD
    }
}
