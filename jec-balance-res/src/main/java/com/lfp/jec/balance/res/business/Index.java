package com.lfp.jec.balance.res.business;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "jec-service-res")
public interface Index {

    @RequestMapping("/")
    String index();

}
