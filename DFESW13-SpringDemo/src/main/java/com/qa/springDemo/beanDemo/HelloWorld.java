package com.qa.springDemo.beanDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring LOVES annotations

// Specifying this class WILL be creating beans
@Configuration
public class HelloWorld {
	
	// This method is considred a 'bean' and put into the beanbag for Spring to access
	@Bean
	public String returnHello() {
		return "Hello World!";
	}

}
