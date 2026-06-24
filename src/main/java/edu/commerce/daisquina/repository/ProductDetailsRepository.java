package edu.commerce.daisquina.repository;

import edu.commerce.daisquina.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDetailsRepository extends CrudRepository<Product, Long> {
}
