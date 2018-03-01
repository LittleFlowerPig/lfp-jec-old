package com.lfp.jec.service.user.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApi {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/")
    public String index() {
        return "Hello World! I am jec-service-user at port: "+ port;
    }

}
