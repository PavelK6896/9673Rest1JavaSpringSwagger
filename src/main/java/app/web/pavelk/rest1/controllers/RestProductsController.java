package app.web.pavelk.rest1.controllers;

import app.web.pavelk.rest1.entities.Product;
import app.web.pavelk.rest1.entities.dtos.ProductDto;
import app.web.pavelk.rest1.exceptions.ProductNotFoundException;
import app.web.pavelk.rest1.services.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//swagger-ui.html
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/p")
@Api("Set of endpoints for CRUD operations for Products")//описание контроллера
public class RestProductsController {
    private ProductsService productsService;

    @Autowired
    public RestProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/dto")//1
    @ApiOperation("Returns list of all products data transfer objects")//описание
    public List<ProductDto> getAllProductsDto() {
        return productsService.getDtoData();
    }

    @GetMapping(produces = "application/json")//2
    @ApiOperation("Returns list of all products")
    public List<Product> getAllProducts() {
        return productsService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json") //3описание что вернет
    @ApiOperation("Returns one product by id")
    public ResponseEntity<?> getOneProduct(@PathVariable
                                           //описали что принемает
                                           @ApiParam("Id of the product to be requested. Cannot be empty") Long id) {
        if (!productsService.existsById(id)) {
            throw new ProductNotFoundException("Product not found, id: " + id);
        }
        return new ResponseEntity<>(productsService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping//4
    @ApiOperation("Removes all products")
    public void deleteAllProducts() {
        productsService.deleteAll();
    }

    @DeleteMapping("/{id}")//5
    @ApiOperation("Removes one product by id")
    public void deleteOneProducts(@PathVariable Long id) {
        productsService.deleteById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")//6 ожидаем и возврощаем
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new product")
    public Product saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return productsService.saveOrUpdate(product);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")//7
    @ApiOperation("Modifies an existing product")
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productsService.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found, id: " + product.getId());
        }
        if (product.getPrice() < 0) {
            return new ResponseEntity<>("Product's price can not be negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ProductNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }
}