package com.letsider.urlshortener.controller;

import com.letsider.urlshortener.models.Url;
import com.letsider.urlshortener.repositories.UrlRepository;
import com.letsider.urlshortener.services.IUrlService;

import com.letsider.urlshortener.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Url errorUrl = new Url();
        if (originalUrl == null || originalUrl.length() == 0) {
            errorUrl.setHashedUrl("Veuillez entrer un URL");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorUrl);
        } else if (!Utils.isUrlValid(originalUrl)) {
            errorUrl.setCompleteUrl(originalUrl);
            errorUrl.setHashedUrl("URL non valide");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorUrl);
        }

        ResponseEntity<Url> response = urlService.shortenUrl(originalUrl);

        return response;

    }

    @CrossOrigin()
    @GetMapping("retrieveUrl")
    public ResponseEntity<Url> retrieveUrl(String hashedUrl) {
        Url errorUrl = new Url();


        if (hashedUrl == null || hashedUrl.isEmpty()) {
            errorUrl.setHashedUrl("Veuillez entrer un URL");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorUrl);
        }

        return urlService.retrieveUrl(hashedUrl);
    }
}
