package com.shopping.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductForm {
	private MensDress product;
	private WomensDress womensDress;
	private KidsDress kidsDress;
    private MultipartFile image;	
}
