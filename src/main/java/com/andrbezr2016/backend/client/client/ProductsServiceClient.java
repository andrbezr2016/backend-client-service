package com.andrbezr2016.backend.client.client;

import com.andrbezr2016.backend.client.dto.Product;
import com.andrbezr2016.backend.client.dto.ProductRequest;
import com.andrbezr2016.backend.client.exception.ClientException;
import com.andrbezr2016.backend.client.exception.ErrorMessage;
import com.andrbezr2016.backend.client.exception.ServerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Component
public class ProductsServiceClient {

    private final RestTemplate restTemplate;

    public ProductsServiceClient(@Qualifier("productsServiceRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getCurrentVersion(UUID id) {
        try {
            return restTemplate.getForObject("/product/{id}/getCurrentVersion", Product.class, id);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public Collection<Product> getPreviousVersions(UUID id) {
        try {
            return restTemplate.exchange("/product/{id}/getPreviousVersions", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Product>>() {
            }, id).getBody();
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public Product getVersionForDate(UUID id, LocalDateTime date) {
        try {
            return restTemplate.getForObject("/product/{id}/getVersionForDate?date={date}", Product.class, id, date);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public Product createProduct(ProductRequest productRequest) {
        try {
            return restTemplate.postForObject("/product/create", productRequest, Product.class);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public void deleteProduct(UUID id) {
        try {
            restTemplate.delete("/product/{id}/delete", id);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public Product rollBackVersion(UUID id) {
        try {
            return restTemplate.patchForObject("/product/{id}/rollBackVersion", null, Product.class, id);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }
}
