package com.onofre.store.adams.infraestructure.repository;

import com.onofre.store.store.infraestructure.dao.StoreDAO;

import java.util.Map;

public interface AdamsRepository {
    Map<String, Object> createDeb(StoreDAO store) throws Exception;
    Map<String, Object> retrieveDeb(String id) throws Exception;
}
