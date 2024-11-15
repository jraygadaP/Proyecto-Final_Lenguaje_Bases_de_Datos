/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Product;
import com.DistritoBelleza.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public void insertProduct(String name, String price) {
        productRepository.insertProduct(name, price);
    }

    public void updateProduct(Long id, String name, String price) {
        productRepository.updateProduct(id, name, price);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}