package com.andrbezr2016.backend.client.controller;

import com.andrbezr2016.backend.client.dto.ProductRequest;
import com.andrbezr2016.backend.client.dto.ProductResponse;
import com.andrbezr2016.backend.client.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}/getCurrentVersion")
    public ProductResponse getCurrentVersion(@PathVariable("id") UUID id) {
        log.info("Get current version of product with id: {}", id);
        return productService.getCurrentVersion(id);
    }

    @GetMapping("/{id}/getPreviousVersions")
    public Collection<ProductResponse> getPreviousVersions(@PathVariable("id") UUID id) {
        log.info("Get previous versions of product with id: {}", id);
        return productService.getPreviousVersions(id);
    }

    @GetMapping("/{id}/getVersionForDate")
    public ProductResponse getVersionForDate(@PathVariable("id") UUID id, @RequestParam("date") OffsetDateTime date) {
        log.info("Get version of product with id: {} for date: {}", id, date);
        return productService.getVersionForDate(id, date);
    }

    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        log.info("Create new product");
        return productService.createProduct(productRequest);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteProduct(@PathVariable("id") UUID id) {
        log.info("Delete product with id: {}", id);
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}/rollBackVersion")
    public ProductResponse rollBackVersion(@PathVariable("id") UUID id) {
        log.info("Roll back product with id: {}", id);
        return productService.rollBackVersion(id);
    }
}
