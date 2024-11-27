package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Category;
import com.example.demo.service.CategoryService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	   @Autowired
	     CategoryService categoryService;

	    @PostMapping("save")
	    public String saveCategory(@RequestBody Category category) {
	    	System.out.println(category);
	      return categoryService.saveCategory(category);
	       
	    }
	    
	    @DeleteMapping("deleteCatById/{id}")
	    public int deleteCategory(@PathVariable int id) {
	        return categoryService.deleteCategoryById(id);
	    }

	    @PutMapping("updateCateById/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
	        System.out.println(category);
	    	Category updatedCategory = categoryService.updateCategoryById(id, category);
	        return ResponseEntity.ok(updatedCategory);  // Return the updated category with 200 status
	    }

	    @GetMapping("getAllCategories")
	    public List<Category> getAllCate() {
	        List<Category> list = categoryService.getAllCategory();
	        return  list;  
	    }


	    
	   

}
