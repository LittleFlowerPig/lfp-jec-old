package com.lfp.jec.balance.user.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexApi {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    public String index() {
        return restTemplate.getForObject("http://jec-service-user/", String.class);
    }

}
