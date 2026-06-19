package edu.commerce.daisquina.controller;

import edu.commerce.daisquina.entity.Product;
import edu.commerce.daisquina.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return  ResponseEntity.ok().body(created);
    }

}
