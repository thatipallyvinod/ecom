package com.shopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.entity.ProductForm;
import com.shopping.entity.WomensDress;

public interface WomensDressService {

	public String addNewWomenDress(ProductForm productForm);
	
	public List<WomensDress> getAllProducts();
	
	public ResponseEntity<byte[]> getWomensDressImageById(long id);
	
	public ProductForm getEditProductForm(long id);
	
	public String getBase64Image(long id);
	
	public WomensDress findDressById(Long id);
	
	public void updateProduct(ProductForm productForm);
	
	public void deleteDress(Long id);
	
	public List<WomensDress> getDressesByCategory(String category);
}
