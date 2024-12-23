package com.andrbezr2016.backend.client.controller;

import com.andrbezr2016.backend.client.dto.TariffRequest;
import com.andrbezr2016.backend.client.dto.TariffResponse;
import com.andrbezr2016.backend.client.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tariff")
@RestController
public class TariffController {

    private final TariffService tariffService;

    @PostMapping("/create")
    public TariffResponse createTariff(@Validated(TariffRequest.Create.class) @RequestBody TariffRequest tariffRequest) {
        log.info("Create new tariff for product with id: {}", tariffRequest.getProduct());
        return tariffService.createTariff(tariffRequest);
    }

    @PatchMapping("/{id}/update")
    public TariffResponse updateTariff(@PathVariable("id") UUID id, @RequestBody TariffRequest tariffRequest) {
        log.info("Update tariff with id: {}", id);
        return tariffService.updateTariff(id, tariffRequest);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteTariff(@PathVariable("id") UUID id) {
        log.info("Delete tariff with id: {}", id);
        tariffService.deleteTariff(id);
    }
}
