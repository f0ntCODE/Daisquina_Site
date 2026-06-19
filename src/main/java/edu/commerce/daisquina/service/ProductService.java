package edu.commerce.daisquina.service;

import edu.commerce.daisquina.entity.Product;
import edu.commerce.daisquina.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {

        this.productRepo = productRepo;

    }

    public Product createNewProduct(Product product){

        if(productRepo.findByName(product.getName()).isPresent()){
            throw new RuntimeException("Product already exists");
        }

        Product newProduct = new Product();
        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setImage(product.getImage());

        return productRepo.save(product);
    }
}
