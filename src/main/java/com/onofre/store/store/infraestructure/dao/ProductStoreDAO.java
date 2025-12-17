package com.onofre.store.store.infraestructure.dao;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductStoreDAO {
    private String id;
    private String name;
    private BigInteger price;
    private BigInteger quantity;
}
