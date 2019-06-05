package com.ty.web;

import com.ty.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxTests {

    @Autowired
    private WebTestClient webClient;


    @Test
    public void addTest() {
        City city = new City();
        city.setCityName("北京");
        city.setDescription("北京市");
        city.setProvinceId(-1L);
        webClient
        .post()
        .uri("/city/add")
        .body(BodyInserters.fromObject(city))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .returnResult()
        .getResponseBody();

    }

}
