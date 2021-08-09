package com.letsider.urlshortener.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
public class Url {
    @Id
    private String id;
    private String completeUrl;
    @Indexed(unique=true)
    private String hashedUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public String getHashedUrl() {
        return hashedUrl;
    }

    public void setHashedUrl(String hashedUrl) {
        this.hashedUrl = hashedUrl;
    }
}
