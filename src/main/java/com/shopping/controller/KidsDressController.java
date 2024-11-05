package com.shopping.controller;

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
import com.shopping.service.KidsDressService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/kids")
public class KidsDressController {

	@Autowired
	private KidsDressService kidsDressService;

	@GetMapping("/add-product")
	public String showAddProductForm(Model model) {
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("baseUrl", "/kids");
		return "add-product";
	}

	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute("productForm") ProductForm productForm,
			RedirectAttributes redirectAttributes) {
		String message = kidsDressService.addNewKidsDress(productForm);
		if (message != null)
			redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/kids/dresses";
	}

	@GetMapping("/dresses")
	public String getProducts(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("dresses", kidsDressService.getAllProducts());
		if (message != null) {
	        model.addAttribute("message", message);
	    }
		return "kidsDresses";
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		return kidsDressService.getKidsDressImageById(id);
	}

	@GetMapping("/edit-dress/{id}")
	public String getProductById(@PathVariable Long id, Model model) {
		model.addAttribute("productForm", kidsDressService.getEditProductForm(id));
		model.addAttribute("base64Image", kidsDressService.getBase64Image(id));
		model.addAttribute("baseUrl", "/kids");
		return "edit-dress";
	}

	@PutMapping("/edit-dress")
	@Transactional
	public String updateProduct(@ModelAttribute("productForm") ProductForm productForm) {
		kidsDressService.updateProduct(productForm);
		return "redirect:/kids/dresses";
	}

	@GetMapping("/delete-dress/{id}")
	public String deleteProduct(@PathVariable Long id) {
		kidsDressService.deleteDress(id);
		return "redirect:/kids/dresses";
	}

	@GetMapping("/dresses/{category}")
	public String getDressByCategory(@PathVariable String category, Model model) {
		model.addAttribute("dresses", kidsDressService.getDressesByCategory(category));
		return "kidsDresses";
	}
}
