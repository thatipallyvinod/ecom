package com.shopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.entity.MensDress;
import com.shopping.entity.ProductForm;

public interface MensDressService {

	public String addNewMensDress(ProductForm productForm);

	public List<MensDress> getAllProducts();

	public ResponseEntity<byte[]> getMensDressImageById(long id);

	public MensDress findDressById(Long id);

	public ProductForm getEditMensDressForm(long id);

	public String getBase64ImageForMens(long id);

	public void updateMensDress(ProductForm productForm);

	public void deleteDress(Long id);

	public List<MensDress> getDressesByCategory(String category);
}
