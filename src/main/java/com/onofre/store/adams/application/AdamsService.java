package com.onofre.store.adams.application;

import com.onofre.store.adams.infraestructure.repository.AdamsRepository;
import com.onofre.store.store.infraestructure.dao.StoreDAO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdamsService {
    private final AdamsRepository repository;

    public AdamsService(AdamsRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> getDebs(String id) throws Exception {
        return repository.retrieveDeb(id);
    }

    public Map<String, Object> createDebs(StoreDAO dao) throws Exception {
        return repository.createDeb(dao);
    }
}
