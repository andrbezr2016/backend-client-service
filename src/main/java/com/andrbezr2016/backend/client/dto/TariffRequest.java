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
public class TariffRequest {

    @NotNull(message = "Name must not be null", groups = {Create.class})
    private String name;
    private String description;
    private UUID product;

    public interface Create {
    }
}
