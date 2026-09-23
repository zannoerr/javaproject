package com.fauzan;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    
    private List<product> productList = new ArrayList<>();
    
    public void addProduct(product product) {
        productList.add(product);
    }
    
    public List<product> findAll() {
        return productList;
    }
    
    public product findById(String id) throws ProductNotFoundException {
        for (product p : productList) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Produk dengan ID '" + id + "' tidak ditemukan!");
    }
}
