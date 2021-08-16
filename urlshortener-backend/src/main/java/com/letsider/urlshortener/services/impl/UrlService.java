package com.letsider.urlshortener.services.impl;

import com.letsider.urlshortener.models.Url;
import com.letsider.urlshortener.services.IUrlService;
import com.letsider.urlshortener.repositories.UrlRepository;

import java.util.zip.CRC32;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UrlService implements IUrlService {

    @Autowired
    private final UrlRepository urlRepository;

    @Value("${urlshortener.domain}")
    String shortDomain;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public ResponseEntity<Url> shortenUrl(String url) {
        Url errorUrl = new Url();

        CRC32 crc32 = new CRC32();
        crc32.update(url.getBytes());
        String result = Long.toHexString(crc32.getValue());

        Url urlToSave = new Url();
        String shortUrl = shortDomain.concat(result);
        try {
            urlToSave.setCompleteUrl(url);
            urlToSave.setHashedUrl(shortUrl);
            urlRepository.save(urlToSave);
        } catch (DuplicateKeyException dke) {
            errorUrl.setCompleteUrl(url);
            errorUrl.setHashedUrl(String.format("URL deja raccourcie : %s", shortUrl));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorUrl);
        }

        return new ResponseEntity<>(urlToSave, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Url> retrieveUrl(String hashedUrl) {
        Url errorUrl = new Url();
        Url url = urlRepository.findByHashedUrl(hashedUrl);
        if (url == null) {
            errorUrl.setCompleteUrl("URL raccourcie inexistante");
            errorUrl.setHashedUrl(hashedUrl);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorUrl);
        } else {
            return new ResponseEntity<>(url, HttpStatus.OK);
        }

    }
}
