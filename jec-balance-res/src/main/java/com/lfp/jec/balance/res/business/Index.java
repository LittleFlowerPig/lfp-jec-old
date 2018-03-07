package com.lfp.jec.balance.res.business;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "jec-service-res", fallback = IndexError.class)
public interface Index {

    @RequestMapping("/")
    String index();

}
