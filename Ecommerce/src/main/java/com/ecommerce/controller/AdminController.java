package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Category;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

@Controller
public class AdminController {
	

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AdminService adminService;



	public AdminController(CategoryService categoryService, AdminService adminService) {
		super();
		this.categoryService = categoryService;
		this.adminService = adminService;
		
	}

	@GetMapping("/admin/new")
	public String createAdmin(Model model) {
		model.addAttribute("admin", new Admin());
		return "adminLogin";
	}

	@PostMapping("/admin")
	public String saveAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "adminLogin"; // "redirect:/students/new"
		}
//		Admin admin1=adminService.findByEmail(admin.getEmail());
		Admin findByEmailAndPassword = adminService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
//		Admin admin2=adminService.findByPassword(admin.getPassword());
		if (findByEmailAndPassword != null) {
			return "adminHome";
		}
		return "adminLogin";

	}

//	@PostMapping("/admin")
//	public String saveAdmin(@RequestBody Admin admin) {
//		
////		Admin admin1=adminService.findByEmail(admin.getEmail());
//		Admin findByEmailAndPassword = adminService.findByEmailAndPassword(admin.getEmail(), );
////		Admin admin2=adminService.findByPassword(admin.getPassword());
//		
//		if (findByEmailAndPassword != null) {
//			return "adminHome";
//		}
//		return "adminLogin";
//
//	}
	

	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String delCat(@PathVariable("id") int id) {
		categoryService.delCategorybyId(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/edit/{id}")
	public String editStudentForm(@PathVariable int id, Model model) {
		model.addAttribute("category", categoryService.getCategorybyId(id));
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/{id}")
	public String updateStudent(@PathVariable int id, @ModelAttribute("category") Category category, Model model) {

		// get student from database by id
		Category existingCategory = categoryService.getCategorybyId(id);
		existingCategory.setId(id);
		existingCategory.setName(category.getName());

		// save updated student object
		categoryService.updateCategory(existingCategory);
		return "redirect:/admin/categories";
	}
	/// Product Section
//
//	@GetMapping("/admin/products")
//	public String getProducts(Model model) {
//		model.addAttribute("products", productService.getAllProduct());
//		return "products";
//	}
//	@GetMapping("/admin/products/add")
//	public String getProductAdd(Model model) {
//		model.addAttribute("product",new Product());
//		model.addAttribute("categories",categoryService.getAllCategory());
//		return "productsAdd";
//	}
//
//	@PostMapping("/admin/products")
//	public String postProductAdd(@ModelAttribute("productDTO") Product product,
//			@RequestParam("productImage")MultipartFile file,
//			@RequestParam("imgName")String imgName) throws IOException {
//		
//		productService.saveProduct(product); 
//		return "redirect:/admin/products";
//	}
}
