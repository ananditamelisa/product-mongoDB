package com.example.productmongo;

import com.example.productmongo.entity.Product;
import com.example.productmongo.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSave(){
        Product product = new Product(1L, "Giraffes Cant Dance",
                "Fables and Animal Stories", "Giles Andrese",
                50000, 50);
        productRepository.save(product).block();
        Product result = productRepository.findById(1L).block();
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindAll(){
        productRepository.deleteAll().block();
        productRepository.save(new Product(1L, "Giraffes Cant Dance",
                "Fables and Animal Stories", "Giles Andrese",
                50000, 50)).block();
        productRepository.save(new Product(3L, "Two Very Naughty Piglets",
                "Fables and Animal Stories", "Lesley Glover",
                50000, 50)).block();
        List<Product> products = productRepository.findAll().collectList().block();
        Assert.assertTrue(products.size()==2);
    }

    @Test
    public void testFindById(){
        productRepository.deleteAll().block();
        productRepository.save(new Product(1L, "Giraffes Cant Dance",
                "Fables and Animal Stories", "Giles Andrese",
                50000, 50)).block();
        productRepository.save(new Product(3L, "Two Very Naughty Piglets",
                "Fables and Animal Stories", "Lesley Glover",
                50000, 50)).block();
        Mono<Product> products = productRepository.findById(3L);
        //Assert.assertTrue(products.);
    }

}