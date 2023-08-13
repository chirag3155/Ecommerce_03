package com.ecommerce.service;
//package com.sheryians.major.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.sheryians.major.model.Product;
//import com.sheryians.major.repository.ProductRepository;
//
//@Service
//public class ProductService {
//	
//	
//	ProductRepository productRepository;
//	public ProductService(ProductRepository productRepository) {
//		super();
//		this.productRepository = productRepository;
//	}
//	
//	public List<Product> getAllProduct(){
//		return productRepository.findAll();
//	}
//
//	public Product saveProduct(Product product) {
//		return productRepository.save(product);
//		
//	}
//	public void delProductyId(long id) {
//		productRepository.deleteById(id);
//	}
//	public Optional<Product> getProductbyId(long id) {
//		return productRepository.findById(id);
//	}
////	public List<Product> getAllProductbyCategoryId(int id){
////		return productRepository.findAllbyCategory_Id(id);
////	}
//}
