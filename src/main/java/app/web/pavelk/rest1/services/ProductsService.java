package app.web.pavelk.rest1.services;


import app.web.pavelk.rest1.entities.Product;
import app.web.pavelk.rest1.entities.dtos.ProductDto;
import app.web.pavelk.rest1.exceptions.ProductNotFoundException;
import app.web.pavelk.rest1.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Can't found product with id = " + id));
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public void deleteAll() {
        productsRepository.deleteAll();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return productsRepository.existsById(id);
    }

    public List<ProductDto> getDtoData() {
        return productsRepository.findAllBy();
    }
}