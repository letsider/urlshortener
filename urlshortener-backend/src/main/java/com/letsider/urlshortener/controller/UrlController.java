package com.letsider.urlshortener.controller;

import com.letsider.urlshortener.models.Url;
import com.letsider.urlshortener.repositories.UrlRepository;
import com.letsider.urlshortener.services.IUrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("url")
public class UrlController {
    private final IUrlService urlService;

    public UrlController(IUrlService urlService) {
        this.urlService = urlService;
    }

    @Autowired
     UrlRepository urlRepository;

    @CrossOrigin()
    @GetMapping("shortenUrl")
    public ResponseEntity<Url> shortenUrl(String originalUrl) {

        ResponseEntity<Url> response = urlService.shortenUrl(originalUrl);

        return response;

    }

    @CrossOrigin()
    @GetMapping("retrieveUrl")
    public ResponseEntity<Url> retrieveUrl(String hashedUrl) {

        ResponseEntity<Url> response = urlService.retrieveUrl(hashedUrl);

        return response;
    }
}
