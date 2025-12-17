package com.onofre.store.store.mapper;

import com.onofre.store.products.infraestructure.dao.ProductDAO;
import com.onofre.store.store.infraestructure.dao.ClientDAO;
import com.onofre.store.store.infraestructure.dao.ProductStoreDAO;
import com.onofre.store.store.infraestructure.dao.StoreDAO;
import com.onofre.store.store.presentation.dto.StoreDTO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class StoreMapper {
    private final MongoTemplate template;

    public StoreMapper(MongoTemplate template) {
        this.template = template;
    }

    public StoreDAO toDAO(StoreDTO dto) {
        StoreDAO storeDAO = new StoreDAO();

        storeDAO.setId(dto.getId());
        storeDAO.setLabel(dto.getLabel());

        ClientDAO clientDAO = new ClientDAO();
        clientDAO.setType(dto.getClient().getType());
        clientDAO.setNumber(dto.getClient().getNumber());
        clientDAO.setLabel(dto.getClient().getLabel());

        storeDAO.setClient(clientDAO);

        List<ProductStoreDAO> products = dto.getProducts().stream()
                        .map(product -> {
                            ProductDAO productDAO = template.findById(product.getId(), ProductDAO.class, "products");

                            ProductStoreDAO line = new ProductStoreDAO();
                            line.setId(productDAO.getId());
                            line.setName(productDAO.getName());
                            line.setPrice(productDAO.getPrice());
                            line.setQuantity(product.getQuantity());
                            return line;
                        }).toList();
        storeDAO.setProducts(products);

        BigInteger amount = products.stream()
                .map(line -> line.getPrice().multiply(line.getQuantity()))
                .reduce(BigInteger.ZERO, BigInteger::add);

        storeDAO.setAmount(amount);

        return storeDAO;
    }

}
