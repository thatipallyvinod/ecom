package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.WomensDress;

public interface WomensDressRepository extends JpaRepository<WomensDress, Long> {
	
	List<WomensDress> findByCategory(String category);
}
