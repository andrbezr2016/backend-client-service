package com.andrbezr2016.backend.client.mapper;

import com.andrbezr2016.backend.client.dto.Product;
import com.andrbezr2016.backend.client.dto.ProductResponse;
import com.andrbezr2016.backend.client.dto.Tariff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "startDate", source = "product.startDate")
    @Mapping(target = "endDate", source = "product.endDate")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "version", source = "product.version")
    @Mapping(target = "tariff", source = "tariff")
    ProductResponse toResponse(Product product, Tariff tariff);
}
