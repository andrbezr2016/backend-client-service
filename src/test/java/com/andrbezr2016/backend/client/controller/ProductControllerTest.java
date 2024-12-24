package com.andrbezr2016.backend.client.controller;

import com.andrbezr2016.backend.client.client.ProductsServiceClient;
import com.andrbezr2016.backend.client.client.TariffsServiceClient;
import com.andrbezr2016.backend.client.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"clientMock"})
class ProductControllerTest {

    private final static String GET_CURRENT_VERSION = "/product/{id}/getCurrentVersion";
    private final static String GET_PREVIOUS_VERSIONS = "/product/{id}/getPreviousVersions";
    private final static String GET_VERSION_FOR_DATE = "/product/{id}/getVersionForDate";
    private final static String CREATE_PRODUCT = "/product/create";
    private final static String DELETE_PRODUCT = "/product/{id}/delete";
    private final static String ROLL_BACK_VERSION = "/product/{id}/rollBackVersion";

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProductsServiceClient productsServiceClient;
    @Autowired
    TariffsServiceClient tariffsServiceClient;

    @Test
    void getCurrentVersionTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID author = UUID.randomUUID();
        String name = "Name";
        String description = "Description";
        LocalDateTime startDate = LocalDateTime.now();
        Product product = Product.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .tariff(null)
                .tariffVersion(null)
                .author(author)
                .version(0L)
                .build();
        ProductResponse productResponse = ProductResponse.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .tariff(null)
                .author(author)
                .version(0L)
                .build();

        doReturn(product).when(productsServiceClient).getCurrentVersion(eq(id));

        mvc.perform(get(GET_CURRENT_VERSION, id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponse)));
    }

    @Test
    void getPreviousVersionsTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID author = UUID.randomUUID();
        UUID tariffId = UUID.randomUUID();
        Long tariffVersion = 1L;
        String name = "Name";
        String description = "Description";
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        Tariff tariff = Tariff.builder()
                .id(tariffId)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .product(id)
                .version(tariffVersion)
                .build();
        Product product = Product.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .tariff(tariffId)
                .tariffVersion(tariffVersion)
                .author(author)
                .version(1L)
                .build();
        List<Product> productList = List.of(product);
        TariffResponse tariffResponse = TariffResponse.builder()
                .id(tariffId)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .product(id)
                .version(tariffVersion)
                .build();
        ProductResponse productResponse = ProductResponse.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .tariff(tariffResponse)
                .author(author)
                .version(1L)
                .build();
        List<ProductResponse> productResponseList = List.of(productResponse);

        doReturn(productList).when(productsServiceClient).getPreviousVersions(eq(id));
        doReturn(tariff).when(tariffsServiceClient).getTariff(eq(tariffId), eq(tariffVersion));

        mvc.perform(get(GET_PREVIOUS_VERSIONS, id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponseList)));
    }

    @Test
    void getVersionForDateTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID author = UUID.randomUUID();
        UUID tariffId = UUID.randomUUID();
        Long tariffVersion = 1L;
        String name = "Name";
        String description = "Description";
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime date = LocalDateTime.now().minusHours(1);
        LocalDateTime endDate = LocalDateTime.now();
        Tariff tariff = Tariff.builder()
                .id(tariffId)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .product(id)
                .version(tariffVersion)
                .build();
        Product product = Product.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .tariff(tariffId)
                .tariffVersion(tariffVersion)
                .author(author)
                .version(1L)
                .build();
        TariffResponse tariffResponse = TariffResponse.builder()
                .id(tariffId)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .product(id)
                .version(tariffVersion)
                .build();
        ProductResponse productResponse = ProductResponse.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .tariff(tariffResponse)
                .author(author)
                .version(1L)
                .build();

        doReturn(product).when(productsServiceClient).getVersionForDate(eq(id), eq(date));
        doReturn(tariff).when(tariffsServiceClient).getTariff(eq(tariffId), eq(tariffVersion));

        mvc.perform(get(GET_VERSION_FOR_DATE, id).queryParam("date", date.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponse)));
    }

    @Test
    void createProductTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID author = UUID.randomUUID();
        String name = "Name";
        String description = "Description";
        LocalDateTime startDate = LocalDateTime.now();
        Product product = Product.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .tariff(null)
                .tariffVersion(null)
                .author(author)
                .version(0L)
                .build();
        ProductResponse productResponse = ProductResponse.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(null)
                .description(description)
                .tariff(null)
                .author(author)
                .version(0L)
                .build();
        ProductRequest productRequest = ProductRequest.builder()
                .name(name)
                .type(Product.ProductType.LOAN)
                .description(description)
                .author(author)
                .build();

        doReturn(product).when(productsServiceClient).createProduct(eq(productRequest));

        mvc.perform(post(CREATE_PRODUCT).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponse)));
    }

    @Test
    void deleteProductTest() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete(DELETE_PRODUCT, id))
                .andExpect(status().isOk());
    }

    @Test
    void rollBackVersionTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID author = UUID.randomUUID();
        UUID tariffId = UUID.randomUUID();
        Long tariffVersion = 1L;
        String name = "Name";
        String description = "Description";
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        Tariff tariff = Tariff.builder()
                .id(tariffId)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .product(id)
                .version(tariffVersion)
                .build();
        Product product = Product.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .tariff(tariffId)
                .tariffVersion(tariffVersion)
                .author(author)
                .version(1L)
                .build();
        TariffResponse tariffResponse = TariffResponse.builder()
                .id(tariffId)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .product(id)
                .version(tariffVersion)
                .build();
        ProductResponse productResponse = ProductResponse.builder()
                .id(id)
                .name(name)
                .type(Product.ProductType.LOAN)
                .startDate(startDate)
                .endDate(endDate)
                .description(description)
                .tariff(tariffResponse)
                .author(author)
                .version(1L)
                .build();

        doReturn(product).when(productsServiceClient).rollBackVersion(eq(id));
        doReturn(tariff).when(tariffsServiceClient).getTariff(eq(tariffId), eq(tariffVersion));

        mvc.perform(patch(ROLL_BACK_VERSION, id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productResponse)));
    }
}