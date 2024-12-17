package com.andrbezr2016.backend.client.service;

import com.andrbezr2016.backend.client.client.ProductsServiceClient;
import com.andrbezr2016.backend.client.dto.Product;
import com.andrbezr2016.backend.client.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductsServiceClient productsServiceClient;

    public Product getCurrentVersion(UUID id) {
        return productsServiceClient.getCurrentVersion(id);
    }

    public Collection<Product> getPreviousVersions(UUID id) {
        return productsServiceClient.getPreviousVersions(id);
    }

    public Product getVersionForDate(UUID id, OffsetDateTime date) {
        return productsServiceClient.getVersionForDate(id, date);
    }

    public Product createProduct(ProductRequest productRequest) {
        return productsServiceClient.createProduct(productRequest);
    }

    public void deleteProduct(UUID id) {
        productsServiceClient.deleteProduct(id);
    }

    public Product rollBackVersion(UUID id) {
        return productsServiceClient.rollBackVersion(id);
    }
}
