package com.example.demo.serviceImpl;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.CategoryDTO;
import com.example.demo.Entity.ProductDTO;
import com.example.demo.Entity.Products;
import com.example.demo.Repo.CategoryRepo;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.customException.NullCategoryException;
import com.example.demo.customException.OperationFailedException;
import com.example.demo.customException.ResourceNotFoundException;
import com.example.demo.service.CategoryService;


@Service
public class CategoryServiceImpl  implements CategoryService{

	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public String saveCategory(Category cate) {
		System.out.println(cate);
	    if (cate == null) {
	        throw new NullCategoryException("Category object is null and cannot be saved.");
	    }
	    categoryRepo.save(cate);
	    return  "Added Category";
	}

	
	@Override
	public int deleteCategoryById(int id) {
	    try {
	        categoryRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found"));

	        
	        
	        List<Products> associatedProducts = productRepo.getAllProductsByCateId(id); // Assumes such a method exists in productRepo
	        if (!associatedProducts.isEmpty()) {
	            return -3; // Category cannot be deleted because it has associated products
	        }
	        
	        categoryRepo.deleteById(id);

	        if (categoryRepo.existsById(id)) {
	            return -1; // Unable to delete
	        }

	        return 1; // Successfully deleted
	    } catch (ResourceNotFoundException e) {
	        return 0; // Category not found
	    } catch (Exception e) {
	        return -2; // Unexpected error
	    }
	}



	

	@Override
	public Category updateCategoryById(int id, Category updatedCategory) {

           System.out.println(updatedCategory);
	    Category existingCategory = categoryRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found"));

	    try {
	        if (updatedCategory.getName() != null) 
	            existingCategory.setName(updatedCategory.getName());
	        if (updatedCategory.getDescription() != null) 
	            existingCategory.setDescription(updatedCategory.getDescription());
	        if (updatedCategory.getPrice() ==0) 
	            existingCategory.setPrice(updatedCategory.getPrice());

	        return categoryRepo.save(existingCategory);
	    } catch (Exception e) {
	        throw new OperationFailedException("An unexpected error occurred while updating the category", e);
	    }
	}

	







	@Override
	public List<Category> getAllCategory() {
		return categoryRepo.getAllCategories();
	}


	@Override
	public Category findCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Category> findCategoriesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
