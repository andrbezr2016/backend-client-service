package com.andrbezr2016.backend.client.service;

import com.andrbezr2016.backend.client.client.ProductsServiceClient;
import com.andrbezr2016.backend.client.client.TariffsServiceClient;
import com.andrbezr2016.backend.client.dto.Product;
import com.andrbezr2016.backend.client.dto.ProductRequest;
import com.andrbezr2016.backend.client.dto.ProductResponse;
import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : productCollection) {
            ProductResponse productResponse = productMapper.toResponse(product, getTariffByProduct(product));
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    public ProductResponse getVersionForDate(UUID id, OffsetDateTime date) {
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

    private Tariff getTariffByProduct(Product product) {
        Tariff tariff = null;
        if (product != null && product.getTariff() != null) {
            tariff = tariffsServiceClient.getTariff(product.getTariff(), product.getTariffVersion());
        }
        return tariff;
    }
}
