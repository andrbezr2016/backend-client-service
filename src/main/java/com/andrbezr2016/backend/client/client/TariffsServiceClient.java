package com.andrbezr2016.backend.client.client;

import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.dto.TariffRequest;
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
        return restTemplate.getForObject("/tariff/{id}?version={version}", Tariff.class, id, version);
    }

    public Collection<Tariff> getTariffs(Collection<UUID> ids) {
        return restTemplate.exchange("/tariff/getAllByIds?ids={ids}", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Tariff>>() {
        }, ids).getBody();
    }

    public Tariff createTariff(TariffRequest tariffRequest) {
        return restTemplate.postForObject("/tariff/create", tariffRequest, Tariff.class);
    }

    public Tariff updateTariff(UUID id, TariffRequest tariffRequest) {
        return restTemplate.patchForObject("/tariff/{id}/update", tariffRequest, Tariff.class, id);
    }

    public void deleteTariff(UUID id) {
        restTemplate.delete("/tariff/{id}/delete", id);
    }
}
