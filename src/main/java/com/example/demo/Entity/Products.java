package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Products {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	int categoryId;
	@NotBlank
	String name;
	int price;
	String description;
}
