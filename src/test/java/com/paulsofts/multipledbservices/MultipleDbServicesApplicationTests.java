package com.paulsofts.multipledbservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paulsofts.multipledbservices.product.data.Product;
import com.paulsofts.multipledbservices.product.repository.ProductRepository;
import com.paulsofts.multipledbservices.user.data.User;
import com.paulsofts.multipledbservices.user.repository.UserRepository;

@SpringBootTest
class MultipleDbServicesApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Test
	void dbTest() {
		
		//creating user
		User user = User.builder()
			.user_name("paulsofts")
			.build();
		//creating product
		Product product = Product.builder()
							.prd_name("HP Envy x 360")
							.prd_price(94460.0)
							.build();
		
		userRepository.save(user);
		productRepository.save(product);
	}

}
