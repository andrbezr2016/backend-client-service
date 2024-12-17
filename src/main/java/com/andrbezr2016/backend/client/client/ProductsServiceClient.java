package com.andrbezr2016.backend.client.client;

import com.andrbezr2016.backend.client.dto.Product;
import com.andrbezr2016.backend.client.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

@Component
public class ProductsServiceClient {

    private final RestTemplate restTemplate;

    public ProductsServiceClient(@Qualifier("productsServiceRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getCurrentVersion(UUID id) {
        return restTemplate.getForObject("/product/{id}/getCurrentVersion", Product.class, id);
    }

    public Collection<Product> getPreviousVersions(UUID id) {
        return restTemplate.exchange("/product/{id}/getPreviousVersions", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Product>>() {
        }, id).getBody();
    }

    public Product getVersionForDate(UUID id, OffsetDateTime date) {
        return restTemplate.getForObject("/product/{id}/getVersionForDate?date={date}", Product.class, id, date);
    }

    public Product createProduct(ProductRequest productRequest) {
        return restTemplate.postForObject("/product/create", productRequest, Product.class);
    }

    public void deleteProduct(UUID id) {
        restTemplate.delete("/product/{id}/delete", id);
    }

    public Product rollBackVersion(UUID id) {
        return restTemplate.patchForObject("/product/{id}/rollBackVersion", null, Product.class, id);
    }
}
