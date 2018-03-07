package com.lfp.jec.balance.user.business;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexApi {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    @HystrixCommand(fallbackMethod = "indexError")
    public String index() {
        return restTemplate.getForObject("http://jec-service-user/", String.class);
    }

    public String indexError() {
        return "Sorry, jec-service-user is error!";
    }

}
