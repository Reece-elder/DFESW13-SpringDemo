package com.qa.springDemo.beanDemo;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring LOVES annotations

// Specifying this class WILL be creating beans
@Configuration
public class HelloWorld {
	
	// This method is considered a 'bean' and put into the beanbag for Spring to access
	@Bean
	public String returnHello() {
//		System.out.println("Hello World!!!");
		return "Hello World!";
	}
	
	@Bean
	public int randomNum() {
		Random newRandom = new Random();
		int num = newRandom.nextInt(11);
		return num;
	}

	@Override
	public String toString() {
		return "HelloWorld [returnHello()=" + returnHello() + "]";
	}
	
	
}
