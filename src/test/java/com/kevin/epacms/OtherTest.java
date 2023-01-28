package com.kevin.epacms;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: kevin
 * @since: 16:58 2022/4/4
 */
@SpringBootTest
public class OtherTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Value("${uploadFile.path}")
    public String path;
    @Test
    public void testPath(){
        System.out.println(path);
    }
    @Test
    public void testDel(){
        String idsJson = "{\"ids\":[20,17]}";
        String idsStr = JSONUtil.parseObj(idsJson).get("ids").toString();
        JSONArray jsonArray = JSONUtil.parseArray(idsStr);
        List<Integer> ids = JSONUtil.toList(jsonArray, Integer.class);
        for (Integer id : ids) {
            System.out.println(id);
        }
    }

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","kevin",2, TimeUnit.MINUTES);
        try {
            Thread.sleep(1000 * 60+1000);
            Long name = redisTemplate.getExpire("name", TimeUnit.SECONDS);
            System.out.println(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
