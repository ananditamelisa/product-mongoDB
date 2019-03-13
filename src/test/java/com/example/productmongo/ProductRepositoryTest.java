package com.example.productmongo;

import com.example.productmongo.entity.Product;
import com.example.productmongo.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
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
        Product products = productRepository.findById(3L).block();
        Assert.assertTrue(products.getName().equals("Two Very Naughty Piglets"));
    }

    @Test
    public void testUpdateProduct(){
        productRepository.deleteAll().block();
        productRepository.save(new Product(1L, "Giraffes Cant Dance",
                "Fables and Animal Stories", "Giles Andrese",
                50000, 50)).block();
        Product product = productRepository.save(new Product(1L, "Giraffes Should Dance",
                "Fables and Animal Stories", "Giles Andrese",
                50000, 50)).block();

        Assert.assertTrue(productRepository.findById(1L).block().getName().equals("Giraffes Should Dance"));
    }

    @Test
    public void testSaveAllProduct(){
        Product product = new Product(1L, "Giraffes Cant Dance",
                "Fables and Animal Stories", "Giles Andrese",
                50000, 50);
        Product product2 = new Product(2L, "Twinkle Little Star",
                "Rhymes and Poetry", "Anastasia Swift",
                50000, 50);
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(product);
        listProduct.add(product2);
        List<Product> p = productRepository.saveAll(listProduct).collectList().block();
        Assert.assertTrue(p.size()==2);
    }

    @Test
    public void testDeleteProduct(){
        Product product = new Product(1L, "Giraffes Cant Dance",
                "Fables and Animal Stories", "Giles Andrese",
                50000, 50);
        productRepository.save(product).block();
        Product product2 = new Product(2L, "Twinkle Little Star",
                "Rhymes and Poetry", "Anastasia Swift",
                50000, 50);
        productRepository.save(product2).block();
        productRepository.deleteById(2L).block();
        Assert.assertNull("Harusnya gaada nih product2",productRepository.findById(2L).block());
    }


}