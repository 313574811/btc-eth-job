package com.alex.txjob.controller;

import com.alex.txjob.feign.FeignApi;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    /**
     * 触发单元测试diff覆盖率
     *
     * @return
     */
    @PostMapping(value = "/1")
    public String test() {

        log.info("test1");
        return "test1";
    }


}
