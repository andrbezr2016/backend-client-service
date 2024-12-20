package com.andrbezr2016.backend.client.service;

import com.andrbezr2016.backend.client.client.ProductsServiceClient;
import com.andrbezr2016.backend.client.client.TariffsServiceClient;
import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.dto.TariffRequest;
import com.andrbezr2016.backend.client.dto.TariffResponse;
import com.andrbezr2016.backend.client.exception.ClientException;
import com.andrbezr2016.backend.client.exception.ErrorMessage;
import com.andrbezr2016.backend.client.mapper.TariffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TariffService {

    private final ProductsServiceClient productsServiceClient;
    private final TariffsServiceClient tariffsServiceClient;
    private final TariffMapper tariffMapper;

    public TariffResponse createTariff(TariffRequest tariffRequest) {
        checkProduct(tariffRequest);
        Tariff tariff = tariffsServiceClient.createTariff(tariffRequest);
        return tariffMapper.toResponse(tariff);
    }

    public TariffResponse updateTariff(UUID id, TariffRequest tariffRequest) {
        checkProduct(tariffRequest);
        Tariff tariff = tariffsServiceClient.updateTariff(id, tariffRequest);
        return tariffMapper.toResponse(tariff);
    }

    public void deleteTariff(UUID id) {
        tariffsServiceClient.deleteTariff(id);
    }

    private void checkProduct(TariffRequest tariffRequest) {
        if (tariffRequest.getProduct() != null && productsServiceClient.getCurrentVersion(tariffRequest.getProduct()) == null) {
            throw new ClientException(ErrorMessage.PRODUCT_NOT_EXIST, tariffRequest.getProduct());
        }
    }
}
