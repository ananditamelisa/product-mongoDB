package com.example.productmongo.controller;

import com.example.productmongo.entity.Product;
import com.example.productmongo.service.ProductService;
import com.example.productmongo.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Path;


@RestController
public class ProductController {
    private ProductService productService;
    private ValidationHelper validationHelper;
    @Autowired
    public ProductController(ProductService productService, ValidationHelper validationHelper) {
        this.productService = productService;
        this.validationHelper = validationHelper;
    }

    @RequestMapping(
            value="/products",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Product> save (@RequestBody Product product){
        return validationHelper.validate(product)
                .flatMap(data->productService.save(data))
                .subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
            value = "/products",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Product> findAll(){
        return productService.findAll().subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
            value = "/products/{idProduct}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Product> findById(@PathVariable("idProduct") Long id){
        return productService.findById(id).subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
            value = "/products/delete/{idProduct}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Product> delete(@PathVariable("idProduct") Long id){
        return productService.delete(id).subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
            value = "/products/update/{idProduct}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Product> update(@RequestBody Product product, @PathVariable("idProduct") Long id){
        return productService.update(product, id).subscribeOn(Schedulers.elastic());
    }

}
