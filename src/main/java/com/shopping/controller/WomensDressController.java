package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.entity.ProductForm;
import com.shopping.entity.WomensDress;
import com.shopping.service.WomensDressService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/womens")
public class WomensDressController {

	@Autowired
	private WomensDressService womensDressService;

	@GetMapping("/add-product")
	public String showAddProductForm(Model model) {
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("baseUrl", "/womens");
		return "add-product";
	}

	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute("productForm") ProductForm productForm,
			RedirectAttributes redirectAttributes) {
		String message = womensDressService.addNewWomenDress(productForm);
		if(message != null)
			redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/womens/dresses";
	}

	@GetMapping("/dresses")
	public String getWomens(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("dresses", womensDressService.getAllProducts());
		if (message != null) {
	        model.addAttribute("message", message);
	    }
		return "womens";
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		return womensDressService.getWomensDressImageById(id);
	}

	@GetMapping("/edit-dress/{id}")
	public String getProductById(@PathVariable Long id, Model model) {	
		model.addAttribute("productForm", womensDressService.getEditProductForm(id));
		model.addAttribute("base64Image", womensDressService.getBase64Image(id));
		model.addAttribute("baseUrl", "/womens");
		return "edit-dress";
	}

	@PutMapping("/edit-dress")
	@Transactional
	public String updateProduct(@ModelAttribute("productForm") ProductForm productForm) {
		womensDressService.updateProduct(productForm);
		return "redirect:/womens/dresses";
	}

	@GetMapping("/delete-dress/{id}")
	public String deleteProduct(@PathVariable Long id) {
		womensDressService.deleteDress(id);
		return "redirect:/womens/dresses";
	}

	@GetMapping("/dresses/{category}")
	public String getDressByCategory(@PathVariable String category, Model model) {
		List<WomensDress> dressesByCategory = womensDressService.getDressesByCategory(category);
		model.addAttribute("dresses", dressesByCategory);
		return "womens";
	}
}
