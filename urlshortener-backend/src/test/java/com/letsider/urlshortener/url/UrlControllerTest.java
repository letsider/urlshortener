package com.letsider.urlshortener.url;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String validUrl = "https://www.google.com/";
    private static final String invalidUrl = "hppt:www.google.com/";

    private static final String shortenService = "shortenUrl";
    private static final String retrieveService = "retrieveUrl";

    private static final String shortenServiceParam = "originalUrl";
    private static final String retrieveServiceParam = "hashedUrl";

    private static final String hashedUrl = "https://url.short/a380745f";
    private static final String badHashedUrl = "https://url.short/wrong";

    @Before
    void init() {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    @Order(1)
    void shortenUrl_url_raccourcie() throws Exception {

        mockMvc.perform(get("/url/" + shortenService).param(shortenServiceParam, validUrl)).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @Order(2)
    void shortenUrl_url_existante() throws Exception {

        mockMvc.perform(get("/url/" + shortenService).param(shortenServiceParam, validUrl)).andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @Order(3)
    void shortenUrl_url_invalid() throws Exception {

        mockMvc.perform(get("/url/" + shortenService).param(shortenServiceParam, invalidUrl)).andDo(print())
                .andExpect(status().isNotAcceptable());

    }

    @Test
    @Order(4)
    void shortenUrl_url_vide() throws Exception {

        mockMvc.perform(get("/url/" + shortenService).param(shortenServiceParam, "")).andDo(print())
                .andExpect(status().isNotAcceptable());

    }

    @Test
    @Order(5)
    void hashed_url_existante() throws Exception {

        mockMvc.perform(get("/url/" + retrieveService).param(retrieveServiceParam, hashedUrl)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void hashedUrl_url_vide() throws Exception {

        mockMvc.perform(get("/url/" + retrieveService).param(retrieveServiceParam, "")).andDo(print())
                .andExpect(status().isNotAcceptable());

    }

    @Test
    @Order(7)
    void hashedUrl_url_inexistante() throws Exception {

        mockMvc.perform(get("/url/" + retrieveService).param(retrieveServiceParam, badHashedUrl)).andDo(print())
                .andExpect(status().isBadRequest());

    }

}
