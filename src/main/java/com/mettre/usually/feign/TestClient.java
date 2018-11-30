package com.mettre.usually.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@FeignClient("comment")
@RestController
public interface TestClient {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String consumer();
}