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

import com.shopping.entity.MensDress;
import com.shopping.entity.ProductForm;
import com.shopping.repository.MensDressRepository;
import com.shopping.service.MensDressService;

@Service
public class MensDressServiceImpl implements MensDressService {

	@Autowired
	private MensDressRepository mensDressRepository;

	public String addNewMensDress(ProductForm productForm) {
		MultipartFile file = productForm.getImage();
		String message = null;
		try {
			MensDress mensDress = productForm.getProduct();
			mensDress.setImage(file.getBytes());
			mensDress.setGender("men");
			mensDressRepository.save(mensDress);
			message = "Product added successfully.";
		} catch (IOException e) {
			e.printStackTrace();
			message = "Failed to add product.";
		}
		return message;
	}

	public List<MensDress> getAllProducts() {
		return mensDressRepository.findAll();
	}

	public ResponseEntity<byte[]> getMensDressImageById(long id) {
		MensDress mensDress = findDressById(id);
		if (mensDress != null && mensDress.getImage() != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(mensDress.getImage());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public MensDress findDressById(Long id) {
		return mensDressRepository.findById(id).get();
	}

	@Override
	public ProductForm getEditMensDressForm(long id) {
		ProductForm productForm = new ProductForm();
		productForm.setProduct(findDressById(id));
		return productForm;
	}

	public String getBase64ImageForMens(long id) {
		MensDress dress = findDressById(id);
		return Base64.getEncoder().encodeToString(dress.getImage());
	}

	public void updateMensDress(ProductForm productForm) {
		MultipartFile file = productForm.getImage();
		try {
			MensDress mensDress = productForm.getProduct();
			if (file != null && !file.isEmpty()) {
				mensDress.setImage(file.getBytes());
			}
			mensDress.setGender("men");
			mensDressRepository.save(mensDress);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteDress(Long id) {
		mensDressRepository.deleteById(id);

	}

	public List<MensDress> getDressesByCategory(String category) {
		return mensDressRepository.findByCategory(category);

	}

}
