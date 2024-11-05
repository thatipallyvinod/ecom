package com.shopping.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.entity.KidsDress;
import com.shopping.entity.ProductForm;
import com.shopping.repository.KidsDressRepository;
import com.shopping.service.KidsDressService;

@Service
public class KidsDressServiceImpl implements KidsDressService {

	@Autowired
	private KidsDressRepository kidsDressRepository;

	public String addNewKidsDress(ProductForm productForm) {
		MultipartFile file = productForm.getImage();
		String message = null;
		try {
			KidsDress kidsDress = productForm.getKidsDress();
			kidsDress.setImage(file.getBytes());
			kidsDress.setGender("kids");
			kidsDressRepository.save(kidsDress);
			message = "Product added successfully.";
		} catch (IOException e) {
			e.printStackTrace();
			message = "Failed to add product.";
		}
		return message;
	}

	public List<KidsDress> getAllProducts() {
		return kidsDressRepository.findAll();
	}

	public ResponseEntity<byte[]> getKidsDressImageById(long id) {
		KidsDress kidsDress = findDressById(id);
		if (kidsDress != null && kidsDress.getImage() != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(kidsDress.getImage());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ProductForm getEditProductForm(long id) {
		ProductForm productForm = new ProductForm();
		productForm.setKidsDress(findDressById(id));
		return productForm;
	}
	
	public String getBase64Image(long id) {
		KidsDress dress = findDressById(id);
		return Base64.getEncoder().encodeToString(dress.getImage());
	}

	public KidsDress findDressById(Long id) {
		return kidsDressRepository.findById(id).get();
	}

	public void updateProduct(ProductForm productForm) {
		MultipartFile file = productForm.getImage();
		try {
			KidsDress kidsDress = productForm.getKidsDress();
			if (file != null && !file.isEmpty()) {
				kidsDress.setImage(file.getBytes());
			}
			kidsDress.setGender("kids");
			kidsDressRepository.save(kidsDress);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteDress(Long id) {
		kidsDressRepository.deleteById(id);
	}

	public List<KidsDress> getDressesByCategory(String category) {
		return kidsDressRepository.findByCategory(category);
	}
}
