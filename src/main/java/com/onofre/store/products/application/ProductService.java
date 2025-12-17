package com.onofre.store.products.application;

import com.onofre.store.products.infraestructure.dao.ProductDAO;
import com.onofre.store.products.infraestructure.repository.ProductsRepository;
import com.onofre.store.products.mapper.ProductMapper;
import com.onofre.store.products.presentation.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductsRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductsRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProductDTO> findAll() throws Exception {
        List<ProductDAO> daos = repository.findAll();
        return daos.stream()
                .map(mapper::toDTO)
                .toList()
        ;
    }

    public ProductDTO findById(String id) throws Exception {
        ProductDAO dao = repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        return mapper.toDTO(dao);
    }

    public ProductDTO save(ProductDTO dto) throws Exception {
        ProductDAO dao = repository.save(mapper.toDAO(dto));
        return mapper.toDTO(dao);
    }

    public ProductDTO update(String id, ProductDTO dto) throws Exception {
        ProductDAO dao = repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        dao.setName(dto.getName());
        dao.setPrice(dto.getPrice());

        return mapper.toDTO(repository.save(dao));
    }

    public void delete(String id) throws Exception {
        repository.deleteById(id);
    }
}
