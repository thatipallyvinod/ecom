package com.shopping.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
//    private String username;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private ROLE role = ROLE.CUSTOMER;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Wishlist wishlist = new Wishlist();
    
    private String mobile;
    
    @OneToMany(mappedBy = "registerDetails", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();
}
