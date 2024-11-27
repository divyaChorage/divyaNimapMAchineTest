package com.example.demo.Repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Category;

public interface CategoryRepo  extends JpaRepository<Category, Integer>{
	
	
	

	 @Query(value = "SELECT * FROM category", nativeQuery = true)
	    List<Category> getAllCategories();
}
