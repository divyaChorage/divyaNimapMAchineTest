package com.example.demo.serviceImpl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Products;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.customException.OperationFailedException;
import com.example.demo.customException.ResourceNotFoundException;
import com.example.demo.service.ProductService;


@Service
public class ProductServiceImpl  implements ProductService{

	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public int saveProduct(int cateId,Products product) {
		
		System.out.println(cateId);
		product.setCategoryId(cateId);
		Products p=  productRepo.save(product);
		if(p!=null)
			return 0;//  i product are  null
		
		return 1;// product added  successfully;
		
	}

	@Override
	public List<Products> getAllProductByCateId(int cateId) {
		   return productRepo.getAllProductsByCateId(cateId);
	}

	@Override
	public int deleteProductById(int productId) {
		 try {
		        productRepo.findById(productId)
		            .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found"));

		        productRepo.deleteById(productId);

		        if (productRepo.existsById(productId)) {
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
	public Products updateProductById(int productid, Products upadtedProduct) 
	{
		   System.out.println("upadtedProduct--"+upadtedProduct);
		    Products  existingProducts = productRepo.findById(productid)
		        .orElseThrow(() -> new ResourceNotFoundException("existingProducts with ID " + productid + " not found"));

		    try {
		        if (upadtedProduct.getName() != null) 
		        	existingProducts.setName(upadtedProduct.getName());
		        if (upadtedProduct.getDescription() != null) 
		        	existingProducts.setDescription(upadtedProduct.getDescription());
		        if (upadtedProduct.getPrice() != 0) 
		        	existingProducts.setPrice(upadtedProduct.getPrice());

		        return productRepo.save(existingProducts);
		    } catch (Exception e) {
		        throw new OperationFailedException("An unexpected error occurred while updating the category", e);
		    }
	}
	
	
	
	
	


}
