package com.shopping.cart.service;

import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

   // @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public void deleteProduct(Integer Id){this.productRepository.deleteById(Id);}

    public Product addProduct(Product product){return productRepository.save(product);}
}