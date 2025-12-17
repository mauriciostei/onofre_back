package com.onofre.store.products.presentation.controller;

import com.onofre.store.products.application.ProductService;
import com.onofre.store.products.presentation.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    @SneakyThrows
    public List<ProductDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ProductDTO find(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    @SneakyThrows
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    @SneakyThrows
    public ProductDTO update(@PathVariable String id, @Valid @RequestBody ProductDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
