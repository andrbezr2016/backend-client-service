package com.andrbezr2016.backend.client.client;

import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.dto.TariffRequest;
import com.andrbezr2016.backend.client.exception.ErrorMessage;
import com.andrbezr2016.backend.client.exception.ServerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
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
        } catch (Exception exception) {
            throw new ServerException(ErrorMessage.SOMETHING_WRONG);
        }
    }

    public Collection<Tariff> getTariffs(Collection<UUID> ids) {
        try {
            return restTemplate.exchange("/tariff/getAllByIds?ids={ids}", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Tariff>>() {
            }, ids).getBody();
        } catch (Exception exception) {
            throw new ServerException(ErrorMessage.SOMETHING_WRONG);
        }
    }

    public Tariff createTariff(TariffRequest tariffRequest) {
        try {
            return restTemplate.postForObject("/tariff/create", tariffRequest, Tariff.class);
        } catch (Exception exception) {
            throw new ServerException(ErrorMessage.SOMETHING_WRONG);
        }
    }

    public Tariff updateTariff(UUID id, TariffRequest tariffRequest) {
        try {
            return restTemplate.patchForObject("/tariff/{id}/update", tariffRequest, Tariff.class, id);
        } catch (Exception exception) {
            throw new ServerException(ErrorMessage.SOMETHING_WRONG);
        }
    }

    public void deleteTariff(UUID id) {
        try {
            restTemplate.delete("/tariff/{id}/delete", id);
        } catch (Exception exception) {
            throw new ServerException(ErrorMessage.SOMETHING_WRONG);
        }
    }
}
