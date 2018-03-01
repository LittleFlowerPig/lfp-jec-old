package com.lfp.jec.balance.res.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApi{

    @Autowired
    private Index index;

    @RequestMapping("/")
    public String index() {
        return index.index();
    }

}
