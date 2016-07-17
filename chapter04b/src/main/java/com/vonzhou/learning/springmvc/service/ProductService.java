package com.vonzhou.learning.springmvc.service;


import com.vonzhou.learning.springmvc.domain.Product;

public interface ProductService {
    Product add(Product product);

    Product get(long id);
}
