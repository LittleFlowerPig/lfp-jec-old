package com.lfp.jec.balance.res.business;

import org.springframework.stereotype.Component;

/**
 * Project: lfp-jec
 * Title:
 * Description:
 * Date: 07
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
@Component
public class IndexError implements Index {

    @Override
    public String index() {
        return "Sorry, jec-service-res is error!";
    }

}
