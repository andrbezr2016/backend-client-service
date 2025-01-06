package com.andrbezr2016.backend.client.controller;

import com.andrbezr2016.backend.client.client.ProductsServiceClient;
import com.andrbezr2016.backend.client.client.TariffsServiceClient;
import com.andrbezr2016.backend.client.dto.Product;
import com.andrbezr2016.backend.client.dto.Tariff;
import com.andrbezr2016.backend.client.dto.TariffRequest;
import com.andrbezr2016.backend.client.dto.TariffResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"clientMock"})
class TariffControllerTest {

    private final static String CREATE_TARIFF = "/tariff/create";
    private final static String UPDATE_TARIFF = "/tariff/{id}/update";
    private final static String DELETE_TARIFF = "/tariff/{id}/delete";

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProductsServiceClient productsServiceClient;
    @Autowired
    TariffsServiceClient tariffsServiceClient;

    @Test
    void createTariffTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID product = UUID.randomUUID();
        String name = "Name";
        String description = "Description";
        LocalDateTime startDate = LocalDateTime.now();
        Tariff tariff = Tariff.builder()
                .id(id)
                .name(name)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .product(product)
                .version(0L)
                .build();
        TariffResponse tariffResponse = TariffResponse.builder()
                .id(id)
                .name(name)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .product(product)
                .version(0L)
                .build();
        TariffRequest tariffRequest = TariffRequest.builder()
                .name(name)
                .description(description)
                .product(product)
                .build();

        doReturn(tariff).when(tariffsServiceClient).createTariff(eq(tariffRequest));
        doReturn(Product.builder().build()).when(productsServiceClient).getCurrentVersion(eq(product));

        mvc.perform(post(CREATE_TARIFF).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(tariffRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(tariffResponse)));
    }

    @Test
    void updateTariffTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID product = UUID.randomUUID();
        String name = "Name";
        String description = "Description";
        LocalDateTime startDate = LocalDateTime.now();
        Tariff tariff = Tariff.builder()
                .id(id)
                .name(name)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .product(product)
                .version(0L)
                .build();
        TariffResponse tariffResponse = TariffResponse.builder()
                .id(id)
                .name(name)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .product(product)
                .version(0L)
                .build();
        TariffRequest tariffRequest = TariffRequest.builder()
                .name(name)
                .description(description)
                .product(product)
                .build();

        doReturn(tariff).when(tariffsServiceClient).updateTariff(eq(id), eq(tariffRequest));
        doReturn(Product.builder().build()).when(productsServiceClient).getCurrentVersion(eq(product));

        mvc.perform(patch(UPDATE_TARIFF, id).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(tariffRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(tariffResponse)));
    }

    @Test
    void deleteTariffTest() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete(DELETE_TARIFF, id))
                .andExpect(status().isOk());
    }
}