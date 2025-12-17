package com.onofre.store.products.infraestructure.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "products")
@Data
public class ProductDAO {

    @Id
    private String id;
    private String name;
    private BigInteger price;
}
