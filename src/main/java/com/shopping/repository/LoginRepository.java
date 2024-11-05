package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopping.entity.RegisterDetails;


public interface LoginRepository extends JpaRepository<RegisterDetails, Long> {

	@Query("SELECT r FROM RegisterDetails r WHERE r.email = :username AND r.password = :password")
	public RegisterDetails findUser(@Param("username") String username, @Param("password") String password);

	public boolean existsByEmail(String email);
	
	@Query("SELECT r FROM RegisterDetails r WHERE r.email = :username AND r.mobile = :mobile")
	public RegisterDetails findPassword(@Param("username") String username, @Param("mobile") String mobile);
}
