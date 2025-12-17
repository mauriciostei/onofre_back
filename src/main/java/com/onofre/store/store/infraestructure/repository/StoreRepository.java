package com.onofre.store.store.infraestructure.repository;

import com.onofre.store.store.infraestructure.dao.StoreDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreRepository extends MongoRepository<StoreDAO, String> {
    List<StoreDAO> findByClientNumberAndStatus(String number, String status);
}
