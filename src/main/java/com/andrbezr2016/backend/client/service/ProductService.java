package com.andrbezr2016.backend.client.service;

import com.andrbezr2016.backend.client.client.ProductsServiceClient;
import com.andrbezr2016.backend.client.client.TariffsServiceClient;
import com.andrbezr2016.backend.client.dto.*;
import com.andrbezr2016.backend.client.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductsServiceClient productsServiceClient;
    private final TariffsServiceClient tariffsServiceClient;
    private final ProductMapper productMapper;

    public ProductResponse getCurrentVersion(UUID id) {
        Product product = productsServiceClient.getCurrentVersion(id);
        return productMapper.toResponse(product, getTariffByProduct(product));
    }

    public Collection<ProductResponse> getPreviousVersions(UUID id) {
        Collection<Product> productCollection = productsServiceClient.getPreviousVersions(id);
        Map<TariffId, Tariff> tariffMap = getTariffsByProduct(id);
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : productCollection) {
            ProductResponse productResponse = productMapper.toResponse(product, tariffMap.get(TariffId.builder().id(product.getTariff()).version(product.getVersion()).build()));
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    public ProductResponse getVersionForDate(UUID id, LocalDateTime date) {
        Product product = productsServiceClient.getVersionForDate(id, date);
        return productMapper.toResponse(product, getTariffByProduct(product));
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productsServiceClient.createProduct(productRequest);
        return productMapper.toResponse(product, null);
    }

    public void deleteProduct(UUID id) {
        productsServiceClient.deleteProduct(id);
    }

    public ProductResponse rollBackVersion(UUID id) {
        Product product = productsServiceClient.rollBackVersion(id);
        return productMapper.toResponse(product, getTariffByProduct(product));
    }

    private Map<TariffId, Tariff> getTariffsByProduct(UUID id) {
        Map<TariffId, Tariff> tariffMap = new HashMap<>();
        Collection<Tariff> tariffList = tariffsServiceClient.getTariffsByProduct(id);
        for (Tariff tariff : tariffList) {
            tariffMap.put(TariffId.builder().id(tariff.getId()).version(tariff.getVersion()).build(), tariff);
        }
        return tariffMap;
    }

    private Tariff getTariffByProduct(Product product) {
        Tariff tariff = null;
        if (product != null && product.getTariff() != null) {
            tariff = tariffsServiceClient.getTariff(product.getTariff(), product.getTariffVersion());
        }
        return tariff;
    }
}
