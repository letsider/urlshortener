package com.letsider.urlshortener.services;

import com.letsider.urlshortener.models.Url;

import org.springframework.http.ResponseEntity;

public interface IUrlService {
    ResponseEntity<Url> shortenUrl(String url);
    ResponseEntity<Url> retrieveUrl(String hashedUrl);
}
