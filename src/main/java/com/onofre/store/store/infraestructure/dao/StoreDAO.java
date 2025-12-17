package com.onofre.store.store.infraestructure.dao;

import com.onofre.store.store.domain.PayStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Document(collection = "store")
@Data
public class StoreDAO {

    @Id
    private String id;
    private String label;
    private ClientDAO client;
    private List<ProductStoreDAO> products;
    private BigInteger amount;
    private PayStatus status = PayStatus.PENDING;
    private String payUrl;
}
