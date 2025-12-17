package com.onofre.store.products.infraestructure.repository;

import com.onofre.store.products.infraestructure.dao.ProductDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<ProductDAO, String> {
}
