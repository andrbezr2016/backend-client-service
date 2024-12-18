package com.andrbezr2016.backend.client.mapper;

import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.dto.TariffResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TariffMapper {

    TariffResponse toResponse(Tariff tariff);
}
