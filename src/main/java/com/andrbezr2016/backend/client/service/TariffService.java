package com.andrbezr2016.backend.client.service;

import com.andrbezr2016.backend.client.client.TariffsServiceClient;
import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.dto.TariffRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TariffService {

    private final TariffsServiceClient tariffsServiceClient;

    public Tariff createTariff(TariffRequest tariffRequest) {
        return tariffsServiceClient.createTariff(tariffRequest);
    }

    public Tariff updateTariff(UUID id, TariffRequest tariffRequest) {
        return tariffsServiceClient.updateTariff(id, tariffRequest);
    }

    public void deleteTariff(UUID id) {
        tariffsServiceClient.deleteTariff(id);
    }
}
