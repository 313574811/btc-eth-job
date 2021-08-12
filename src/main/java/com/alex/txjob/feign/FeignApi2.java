package com.alex.txjob.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feignApi",url = "https://chain.api.btc.com/v3/")
public interface FeignApi2 {

    @RequestMapping(value = "block/latest/tx",method = RequestMethod.GET)
    public JSONObject callBtc();

//    @RequestMapping(value = "rawblock/300000",method = RequestMethod.GET)
//    public JSONObject callBtc2();
}
