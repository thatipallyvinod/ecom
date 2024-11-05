package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.MensDress;

public interface MensDressRepository extends JpaRepository<MensDress, Long> {

	List<MensDress> findByCategory(String category);
}
