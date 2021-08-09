package com.letsider.urlshortener;

import com.letsider.urlshortener.services.IUrlService;
import com.letsider.urlshortener.services.impl.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import  com.letsider.urlshortener.repositories.UrlRepository;

@SpringBootApplication
public class UrlshortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlshortenerApplication.class, args);
	}
	
	@Autowired
	public UrlRepository urlRepository;

	@Bean
	public IUrlService urlService() {
		return new UrlService(urlRepository);
	}

}
