package com.paulsofts.multipledbservices.product.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_information")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int prd_id;
	private String prd_name;
	private double prd_price;
}
