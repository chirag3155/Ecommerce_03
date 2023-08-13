package com.ecommerce.model;
//package com.sheryians.major.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotEmpty;
//
//@Entity
//public class Product {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private long id;
//	@NotEmpty
//	@Column(nullable=false)
//	private String name;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="category_id",referencedColumnName="id")
//	public Category category;
//	private double price;
//	private double weight;
//	private String description;
//	private String imageName;
//	
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public Product(long id, String name) {
//		super();
//		this.id = id;
//		this.name = name;
//	}
//	public Product(String name) {
//		super();
//		this.name = name;
//	}
//	
//	public Product() {
//		
//	}
//
//}
