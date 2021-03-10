package com.target.myretail.service;

import com.target.myretail.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface ProductService {

    Optional<Product> findProductsById(String id);

    Product updateProduct(Product product);

    void deleteProductsById(String id);

    void deleteProducts();

    List<Product> findAll(int page, int size);

    List<Product> findAll();

}
