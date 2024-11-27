package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Products;

public interface ProductService {

	
	int saveProduct(int productId,Products cate);
	 List<Products> getAllProductByCateId(int cateId);
	 public int deleteProductById(int productId);
	 public Products updateProductById(int id,Products product);


}
