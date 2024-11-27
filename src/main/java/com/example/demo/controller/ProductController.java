package com.example.demo.controller;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Products;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	
	   @Autowired
	     ProductService productService;
	   
	   @Autowired
	   ProductRepo productRepo;
	   
	   
	   @PostMapping("saveProduct/{cateId}")
	    public int saveCategory(@PathVariable int cateId,@RequestBody Products product) {
			System.out.println(cateId);
	      return productService.saveProduct(cateId,product);
	       
	    }
	   
	   @GetMapping("getAllProductsByCategoryId/{cateId}")
	    public List<Products> getAllproduct(@PathVariable int cateId) {
	        List<Products> list = productService.getAllProductByCateId(cateId);
	        return  list;  
	    }
	   
	   
	   @DeleteMapping("deleteProductById/{productid}")
	    public int deleteProduct(@PathVariable int productid) {
	        return productService.deleteProductById(productid);
	    }
	   
	   @PutMapping("updateProductById/{productid}")
	    public ResponseEntity<Products> updateCategory(@PathVariable int productid, @RequestBody Products products) {
	        System.out.println(products);
	    	Products updatedCategory = productService.updateProductById(productid, products);
	        return ResponseEntity.ok(updatedCategory);  // Return the updated category with 200 status
	    }
	   @GetMapping("categories/{categoryId}/products")
	   public ResponseEntity<Page<Products>> getProductsByCategory(
	       @PathVariable int categoryId,
	       @RequestParam(defaultValue = "0") int page,
	       @RequestParam(defaultValue = "10") int size) {

	       Page<Products> products = productRepo.findByCategoryId(categoryId, PageRequest.of(page, size));

	       if (products.isEmpty()) {
	           return ResponseEntity.noContent().build();
	       }
	       return ResponseEntity.ok(products);
	   }        



}
