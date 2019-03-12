package com.example.productmongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @NotNull
    @Id
    private Long id;
    @NotBlank(message="NotBlank")
    private String name;
    @NotBlank(message="NotBlank")
    private String category;
    @NotBlank(message="NotBlank")
    private String writer;
    @NotNull
    private int price;
    @NotNull
    private int stock;
}
