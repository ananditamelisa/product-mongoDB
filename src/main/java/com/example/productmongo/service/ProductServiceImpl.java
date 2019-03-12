package com.example.productmongo.service;

import com.example.productmongo.entity.Product;
import com.example.productmongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Mono<Product> update(Product product, Long id) {
        return productRepository.findById(id)
                .map(value->new Product(value.getId(), product.getName(), product.getCategory(),
                        product.getWriter(), product.getPrice(),product.getStock()))
                .flatMap(value-> productRepository.save(value).thenReturn(value));
    }

    @Override
    public Mono<Product> delete(Long id) {
        return productRepository.findById(id)
                .flatMap(value-> productRepository.deleteById(id).thenReturn(value));
    }
}
