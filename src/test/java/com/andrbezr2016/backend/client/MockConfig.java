package com.andrbezr2016.backend.client;

import com.andrbezr2016.backend.client.client.ProductsServiceClient;
import com.andrbezr2016.backend.client.client.TariffsServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
public class MockConfig {

    @Bean
    @Primary
    @Profile("clientMock")
    public ProductsServiceClient productsServiceClientMock() {
        return mock(ProductsServiceClient.class);
    }

    @Bean
    @Primary
    @Profile("clientMock")
    public TariffsServiceClient tariffsServiceClientMock() {
        return mock(TariffsServiceClient.class);
    }
}