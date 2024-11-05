package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopping.entity.Admin;

public interface AdminRegisterRepository extends JpaRepository<Admin, Long>{
	
	@Query("SELECT r FROM Admin r WHERE r.email = :email AND r.authId = :authId")
	public Admin findUser(@Param("email") String email, @Param("authId") String authId);
}
