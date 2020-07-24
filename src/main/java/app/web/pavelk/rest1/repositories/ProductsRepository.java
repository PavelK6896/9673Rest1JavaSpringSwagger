package app.web.pavelk.rest1.repositories;


import app.web.pavelk.rest1.entities.Product;
import app.web.pavelk.rest1.entities.dtos.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<ProductDto> findAllBy();
}