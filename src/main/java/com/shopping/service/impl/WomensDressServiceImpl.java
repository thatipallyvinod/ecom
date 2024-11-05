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

import com.shopping.entity.ProductForm;
import com.shopping.entity.WomensDress;
import com.shopping.repository.WomensDressRepository;
import com.shopping.service.WomensDressService;

@Service
public class WomensDressServiceImpl implements WomensDressService{

	@Autowired
	private WomensDressRepository womensDressRepository;

	public String addNewWomenDress(ProductForm productForm) {
		MultipartFile file = productForm.getImage();
		String message = null;
		try {
			WomensDress womensDress = productForm.getWomensDress();
			womensDress.setImage(file.getBytes());
			womensDress.setGender("women");
			womensDressRepository.save(womensDress);
			message = "Product added successfully.";
		} catch (IOException e) {
			e.printStackTrace();
			message = "Failed to add product.";
		}
		return message;
	}

	public List<WomensDress> getAllProducts() {
		return womensDressRepository.findAll();
	}

	public ResponseEntity<byte[]> getWomensDressImageById(long id) {
		WomensDress womensDress = findDressById(id);
		if (womensDress != null && womensDress.getImage() != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(womensDress.getImage());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ProductForm getEditProductForm(long id) {
		ProductForm productForm = new ProductForm();
		productForm.setWomensDress(findDressById(id));
		return productForm;
	}
	
	public String getBase64Image(long id) {
		WomensDress dress = findDressById(id);
		return Base64.getEncoder().encodeToString(dress.getImage());
	}

	public WomensDress findDressById(Long id) {
		return womensDressRepository.findById(id).get();
	}
	
	public void updateProduct(ProductForm productForm) {
		MultipartFile file = productForm.getImage();
		try {
			WomensDress womensDress = productForm.getWomensDress();
			if (file != null && !file.isEmpty()) {
				womensDress.setImage(file.getBytes());
			}
			womensDress.setGender("women");
			womensDressRepository.save(womensDress);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteDress(Long id) {
		womensDressRepository.deleteById(id);
	}

	public List<WomensDress> getDressesByCategory(String category) {
		return womensDressRepository.findByCategory(category);
	}

}
