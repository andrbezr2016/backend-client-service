package com.andrbezr2016.backend.client.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties("backend-client-service.client")
public class ClientProperties {

    @ConstructorBinding
    public ClientProperties(String productsServiceUrl, String tariffsServiceUrl) {
        this.productsServiceUrl = productsServiceUrl;
        this.tariffsServiceUrl = tariffsServiceUrl;
    }

    private final String productsServiceUrl;
    private final String tariffsServiceUrl;
}
