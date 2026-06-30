package edu.commerce.daisquina.controller;

import edu.commerce.daisquina.entity.Product;
import edu.commerce.daisquina.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService  productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint para a criação de um produto no sistema
     * @param product Objeto produto
     * @return corpo da resposta, este sendo o produto criado
     */
    @PostMapping("/register")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){

        String productName = product.getName();
        product.setName(productName.toLowerCase().trim());

        Product created = productService.createNewProduct(product);

        return  ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product){

        Product updated = productService.editProduct(id, product);

        return ResponseEntity.ok().body(updated);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
