package com.andrbezr2016.backend.client.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class TariffId {

    private UUID id;
    private Long version;
}
