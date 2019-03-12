package com.example.productmongo.service;

import com.example.productmongo.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> save (Product product);
    Flux<Product> findAll();
    Mono<Product>findById (Long id);
    Mono<Product>update(Product product, Long id);
    Mono<Product>delete (Long id);
}
