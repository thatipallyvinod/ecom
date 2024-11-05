package com.shopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.entity.KidsDress;
import com.shopping.entity.ProductForm;

public interface KidsDressService {

	public String addNewKidsDress(ProductForm productForm);

	public ResponseEntity<byte[]> getKidsDressImageById(long id);

	public List<KidsDress> getAllProducts();

	public ProductForm getEditProductForm(long id);

	public String getBase64Image(long id);

	public KidsDress findDressById(Long id);

	public void deleteDress(Long id);

	public void updateProduct(ProductForm productForm);

	public List<KidsDress> getDressesByCategory(String category);
}
