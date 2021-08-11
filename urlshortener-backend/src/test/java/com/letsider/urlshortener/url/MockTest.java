package com.letsider.urlshortener.url;

import com.letsider.urlshortener.controller.UrlController;
import com.letsider.urlshortener.repositories.UrlRepository;
import com.letsider.urlshortener.services.impl.UrlService;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlController.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class MockTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UrlRepository urlRepository;

  @MockBean
  private UrlService urlService;

  private static final String validUrl = "https://www.google.com/";

  private static final String shortenServicePath = "/url/shortenUrl";
  private static final String retrieveServicePath = "/url/retrieveUrl";

  private static final String shortenServiceParam = "originalUrl";
  private static final String retrieveServiceParam = "hashedUrl";

  private static final String hashedUrl = "https://url.short/a380745f";

  private static final String jsonReturnHashedOk = "{\"id\":null,\"completeUrl\":\"https://www.google.com/\",\"hashedUrl\":\"https://url.short/a380745f\"}";

  private static final String jsonReturnHashedKoNotFound = "{\"id\":null,\"completeUrl\":\"URL raccourcie inexistante\",\"hashedUrl\":\"https://url.short/a380745f\"}";

  @Test
  @Order(1)
  public void shortenUrl_url_raccourcie() throws Exception {
    this.mvc.perform(get(shortenServicePath).param(shortenServiceParam, validUrl))
        .andExpect(content().json(jsonReturnHashedOk));

  }

  @Test
  @Order(2)
  void hashed_url_inexistante() throws Exception {
    this.mvc.perform(get(retrieveServicePath).param(retrieveServiceParam, hashedUrl))
        .andExpect(content().json(jsonReturnHashedKoNotFound));
  }

}
