package com.onofre.store.store.presentation.controller;

import com.onofre.store.store.application.StoreService;
import com.onofre.store.store.infraestructure.dao.StoreDAO;
import com.onofre.store.store.presentation.dto.StoreDTO;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @PostMapping("/webhook")
    @SneakyThrows
    public void receiveUpdate(@RequestBody Map<String, Object> body) {
        service.updateStores(body);
    }

    @GetMapping("/{ci}")
    @SneakyThrows
    public List<StoreDAO> fetchStores(@PathVariable String ci) {
        return service.findStores(ci);
    }

    @PostMapping
    @SneakyThrows
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@Valid @RequestBody StoreDTO dto) {
        return service.create(dto);
    }
}
