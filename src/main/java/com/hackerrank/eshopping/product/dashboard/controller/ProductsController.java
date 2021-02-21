package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    ProductsService productService;

    @GetMapping("/{productId}")
    private ResponseEntity<Optional<Product>> findProductById(@PathVariable("productId") Long productId) {
        Optional<Product> product = productService.getProductById(productId);

        return product.isPresent()? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    private ResponseEntity addProduct(@Valid @RequestBody Product product ) {

        return productService.addProduct(product) ? new ResponseEntity<>(HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{productId}")
    private ResponseEntity updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {

       return productService.updateProductById(product, productId) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    private ResponseEntity<List<Product>> findProductsByCategoryAndAvailability(@RequestParam(name="category", required = false) String category, @RequestParam(name="availability", required = false) Integer availability) {

        return new ResponseEntity<>(productService.findProduct(category, availability),HttpStatus.OK);
    }

}
