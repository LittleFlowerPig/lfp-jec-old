package com.lfp.jec.service.res.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//配置可以动态刷新
@RefreshScope
public class IndexApi {

    @Value("${server.port}")
    private String port;

    @Value("${config.type}")
    private String config;

    @Value("${database.type}")
    private String database;

    @RequestMapping("/")
    public String index() {
        return "Hello World! I am jec-service-res at port: "+port+
                ", config.type is "+config+
                ", database.type is "+database;
    }

}
