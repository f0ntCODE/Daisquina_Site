package edu.commerce.daisquina.service;

import edu.commerce.daisquina.entity.Product;
import edu.commerce.daisquina.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {

        this.productRepo = productRepo;

    }

    @Transactional
    public Product createNewProduct(Product product){

        if(productRepo.findByName(product.getName()).isPresent()){
            throw new RuntimeException("Product already exists");
        }

        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setImage(product.getImage());

        return productRepo.save(newProduct);
    }

    @Transactional
    public Product editProduct(Long id, Product product){
        Product prodFound = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("There's no product with this id"));

        prodFound.setName(product.getName());
        prodFound.setDescription(product.getDescription());
        prodFound.setPrice(product.getPrice());

        productRepo.save(prodFound);

        return prodFound;

    }

    @Transactional
    public void deleteProduct(Long id){
        Product prodFound = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("There's no product with this id"));

        productRepo.delete(prodFound);
    }

}
