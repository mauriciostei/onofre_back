package com.onofre.store.products.mapper;

import com.onofre.store.products.infraestructure.dao.ProductDAO;
import com.onofre.store.products.presentation.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(ProductDAO dao) {
        ProductDTO dto = new ProductDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setPrice(dao.getPrice());
        return dto;
    }

    public ProductDAO toDAO(ProductDTO dto) {
        ProductDAO dao = new ProductDAO();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setPrice(dto.getPrice());
        return dao;
    }
}
