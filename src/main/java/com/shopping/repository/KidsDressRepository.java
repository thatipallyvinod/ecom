package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.KidsDress;

public interface KidsDressRepository extends JpaRepository<KidsDress, Long>{

	
	List<KidsDress> findByCategory(String category);
}
