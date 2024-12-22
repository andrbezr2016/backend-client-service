package com.andrbezr2016.backend.client.client;

import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.dto.TariffRequest;
import com.andrbezr2016.backend.client.exception.ClientException;
import com.andrbezr2016.backend.client.exception.ErrorMessage;
import com.andrbezr2016.backend.client.exception.ServerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class TariffsServiceClient {

    private final RestTemplate restTemplate;

    public TariffsServiceClient(@Qualifier("tariffsServiceRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Tariff getTariff(UUID id, Long version) {
        try {
            return restTemplate.getForObject("/tariff/{id}?version={version}", Tariff.class, id, version);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public Tariff createTariff(TariffRequest tariffRequest) {
        try {
            return restTemplate.postForObject("/tariff/create", tariffRequest, Tariff.class);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public Tariff updateTariff(UUID id, TariffRequest tariffRequest) {
        try {
            return restTemplate.patchForObject("/tariff/{id}/update", tariffRequest, Tariff.class, id);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }

    public void deleteTariff(UUID id) {
        try {
            restTemplate.delete("/tariff/{id}/delete", id);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().is4xxClientError()) {
                throw new ClientException(exception.getMessage(), exception);
            } else {
                throw new ServerException(ErrorMessage.PROBLEM_WITH_INTERNAL, exception);
            }
        }
    }
}
