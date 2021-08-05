package com.chen.sample1.web.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.chen.sample1.tool.message.ResponseMsg;
import com.chen.sample1.web.service.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @desc
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICacheService cacheService;


    @GetMapping("/hello")
    public ResponseMsg hello() {
        ResponseMsg responseMsg = new ResponseMsg();
        String message = "hello world! time:"+ DateUtil.formatDateTime(new Date());
        responseMsg.setData(message);

        String cacheKey = "hello";
        cacheService.put(cacheKey, JSONUtil.toJsonStr(responseMsg));

        ResponseMsg res = cacheService.get(cacheKey,ResponseMsg.class);
        logger.info(JSONUtil.toJsonStr(res));

        return responseMsg;
    }
}
