package com.hackerrank.eshopping.product.dashboard.repo;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

    @Repository
    public interface ProductsRepo extends JpaRepository<Product, Long> {

        List<Product> findByCategory(String category, Sort sortParam);
        List<Product> findByCategoryAndAvailability(String category, boolean availability, Sort sortParam);

}
