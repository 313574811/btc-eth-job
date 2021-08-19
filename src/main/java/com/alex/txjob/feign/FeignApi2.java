package com.alex.txjob.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feignApi2",url = "https://www.baidu.com/")
public interface FeignApi2 {

    @RequestMapping(value = "/s?wd=aiqiyi",method = RequestMethod.GET)
    public JSONObject callBtc();

//    @RequestMapping(value = "rawblock/300000",method = RequestMethod.GET)
//    public JSONObject callBtc2();
}
