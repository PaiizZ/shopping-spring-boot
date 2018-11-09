package com.example.shopping.controllers;

import com.example.shopping.ShoppingApplication;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.skyscreamer.jsonassert.JSONAssert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class ProductControllerTest {
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private final String port = "8080";
    @Before
    public void setup() {
        this.headers.add("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYWlpenoiLCJleHAiOjE1NDI0MzQzODl9.tz4UDYRscqzedXDJmkV1s9gydHM3xMl2aikv-SjJza3P_jnm4VHv32DTTfya_7_Bmx2s27VabnJ85n0xcwgeWw");
    }

    @Test
    public void createProduct() throws JSONException {

    }

    @Test
    public void findProductById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/products/1"),
                HttpMethod.GET, entity, String.class);

        String expected = "{name:Pizza,price:299,product_id:1}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:"+ port + uri;
    }
}