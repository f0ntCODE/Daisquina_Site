package edu.commerce.daisquina.repository;

import edu.commerce.daisquina.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
    Slice<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
