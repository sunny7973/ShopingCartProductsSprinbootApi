package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.repo.ProductsRepo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    ProductsRepo productRepo;

    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

    public boolean productExists(Long id) {
        return productRepo.existsById(id);
    }

    public boolean addProduct(Product product) {
        if(!productRepo.existsById(product.getId())) {
            productRepo.save(product);
            logger.info("Product saved successfully");
            return true;
        }
        logger.error("Error saving product");
        return false;
    }

    public Optional<Product> getProductById(Long id) {
        return this.productRepo.findById(id);
    }

    public boolean updateProductById(Product product, Long productId) {
        if(productExists(productId)) {
            product.setId(productId);
            productRepo.save(product);
            logger.info("Updated product saved");
            return true;
        }
        logger.error("Error updating product");
        return false;
    }

    public List<Product> findAllProducts() {
        return this.productRepo.findAll(Sort.by("id").ascending());
    }

    public List<Product> findByCategory(String category) {
        return this.productRepo.findByCategory(category, Sort.by("availability").descending()
                .and(Sort.by("discountedPrice").ascending()).and(Sort.by("id").ascending()));
    }

    public List<Product> findByCategoryAndAvailability(String category, boolean isAvailable) {
        return this.productRepo.findByCategoryAndAvailability(category, isAvailable,
                Sort.by("percentage").descending().and(Sort.by(Sort.Direction.ASC, "discountedPrice","id")));
    }

    public List<Product> findProduct(String category, Integer availability){
        List<Product> products = null;
        if(StringUtils.isNotBlank(category) &&  availability != null) {
            boolean isAvailable = availability == 1;
            try {
                products = findByCategoryAndAvailability(URLDecoder.decode(category, StandardCharsets.UTF_8.name()), isAvailable);
            } catch (UnsupportedEncodingException e) {
                logger.error("Exception finding product", e);
            }
        } else if(StringUtils.isNotBlank(category)) {
            logger.info("Calling findByCategory");
            products = findByCategory(category);
        } else {
            logger.info("Calling findAllProducts");
            products = findAllProducts();
        }
        return products;
    }

}
