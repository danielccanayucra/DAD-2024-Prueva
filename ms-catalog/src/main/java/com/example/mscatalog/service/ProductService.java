package com.example.mscatalog.service;

import com.example.mscatalog.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> list();

    public Optional<Product> getById(Integer id);

    public Product save(Product product);

    public Product update(Integer id, Product product);

    public void delete(Integer id);

}
