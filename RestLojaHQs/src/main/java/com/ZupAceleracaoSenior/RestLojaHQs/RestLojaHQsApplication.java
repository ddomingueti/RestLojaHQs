package com.ZupAceleracaoSenior.RestLojaHQs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestLojaHQsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestLojaHQsApplication.class, args);
	}

}
