package com.mone.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2814:32
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PatchMapping
    public void sendResisMessage(String msg){
        stringRedisTemplate.convertAndSend("goods", msg);
    }

}
