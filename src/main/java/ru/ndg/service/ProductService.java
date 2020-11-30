package ru.ndg.service;

import org.springframework.data.domain.Page;
import ru.ndg.model.Product;

import java.util.Map;
import java.util.Optional;

public interface ProductService {

    Page<Product> getAllProducts(Map<String, String> params, Integer page);
    Optional<Product> getProductById(Long id);
    Product saveOrUpdateProduct(Product product);
    void deleteProductById(Long id);
    void deleteAllProduct();
}
