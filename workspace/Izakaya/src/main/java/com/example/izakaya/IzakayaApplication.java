package com.example.izakaya;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.izakaya.dao")
@SpringBootApplication
public class IzakayaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IzakayaApplication.class, args);
	}

}
