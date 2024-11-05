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

import com.shopping.entity.MensDress;
import com.shopping.entity.ProductForm;
import com.shopping.service.MensDressService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/mens")
public class MensDressController {

	@Autowired
	private MensDressService mensDressService;

	@GetMapping("/add-product")
	public String showAddProductForm(Model model) {
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("baseUrl", "/mens");
		return "add-product";
	}

	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute("productForm") ProductForm productForm,
			RedirectAttributes redirectAttributes) {
		String message = mensDressService.addNewMensDress(productForm);
		if (message != null)
			redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/mens/dresses";
	}

	@GetMapping("/dresses")
	public String getProducts(Model model, @ModelAttribute("message") String message) {
		List<MensDress> mensDresses = mensDressService.getAllProducts();
		model.addAttribute("dresses", mensDresses);
		if (message != null)
			model.addAttribute("message", message);
		return "mensDresses";
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		return mensDressService.getMensDressImageById(id);
	}

	@GetMapping("/edit-dress/{id}")
	public String getProductById(@PathVariable Long id, Model model) {
		model.addAttribute("productForm", mensDressService.getEditMensDressForm(id));
		model.addAttribute("base64Image", mensDressService.getBase64ImageForMens(id));
		model.addAttribute("baseUrl", "/mens");
		return "edit-dress";
	}

	@PutMapping("/edit-dress")
	@Transactional
	public String updateProduct(@ModelAttribute("productForm") ProductForm productForm) {
		mensDressService.updateMensDress(productForm);
		return "redirect:/mens/dresses";
	}

	@GetMapping("/delete-dress/{id}")
	public String deleteProduct(@PathVariable Long id) {
		mensDressService.deleteDress(id);
		return "redirect:/mens/dresses";
	}

	@GetMapping("/dresses/{category}")
	public String getDressByCategory(@PathVariable String category, Model model) {
		model.addAttribute("dresses", mensDressService.getDressesByCategory(category));
		return "mensDresses";
	}

}
