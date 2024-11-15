/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DistritoBelleza.Repository;

import com.DistritoBelleza.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getProductsCall;
    private SimpleJdbcCall insertProductCall;
    private SimpleJdbcCall updateProductCall;
    private SimpleJdbcCall deleteProductCall;

    @PostConstruct
    public void init() {
        getProductsCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_Products")
            .returningResultSet("p_cursor", (rs, rowNum) -> {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                return product;
            });

        insertProductCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("insert_product");

        updateProductCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("update_product");

        deleteProductCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("delete_product");
    }

    public List<Product> getProducts() {
        Map<String, Object> result = getProductsCall.execute();
        return (List<Product>) result.get("p_cursor");
    }

    public void insertProduct(String name, String price) {
        insertProductCall.execute(Map.of("p_name", name, "p_price", price));
    }

    public void updateProduct(Long id, String name, String price) {
        updateProductCall.execute(Map.of("p_id", id, "p_name", name, "p_price", price));
    }

    public void deleteProduct(Long id) {
        deleteProductCall.execute(Map.of("p_id", id));
    }
}