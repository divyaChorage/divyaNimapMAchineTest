package com.example.demo.Repo;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Products;

public interface ProductRepo extends JpaRepository<Products, Integer> {

	@Query(value = "SELECT * FROM products WHERE category_id = :category_id", nativeQuery = true)
	List<Products> getAllProductsByCateId(@Param("category_id") int categoryId);

    Page<Products> findByCategoryId(int categoryId, Pageable pageable);


}
