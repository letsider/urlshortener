package com.letsider.urlshortener.repositories;


import com.letsider.urlshortener.models.Url;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
    Url findByHashedUrl(String hashedUrl);
}
