package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByEmail(String email);

	Admin findByPassword(String password);

//	Admin findByEmailAndPassword(String email,String password);
	Admin findByEmailAndPassword(String email, String password);

}
