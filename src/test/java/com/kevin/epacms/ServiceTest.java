package com.kevin.epacms;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kevin.epacms.entity.Record;
import com.kevin.epacms.entity.dto.RecordMap;
import com.kevin.epacms.mapper.RecordMapper;
import com.kevin.epacms.service.NewsService;
import com.kevin.epacms.service.RecordService;
import com.kevin.epacms.service.UserService;
import com.kevin.epacms.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@SpringBootTest
class ServiceTest {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sendMail;

    @Test
    public void testMail(){
        //随机生成一个四位数的验证码
        int code = new SecureRandom().nextInt(9999) % (9999 - 1000 + 1) + 1000;
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //发送邮件账户
        message.setFrom(sendMail);
        //谁要接收
        message.setTo("1538281872@qq.com");
        //邮件标题
        message.setSubject("验证码");
        //邮件内容
        message.setText("您的验证码是"+code);
        javaMailSender.send(message);
    }

    @Autowired
    private UserService userService;

    @Test
    public void testUser(){
        Result result = userService.deleteUsers("1,2,3");
        System.out.println(result.getMessage());
    }

    @Autowired
    private RecordService recordService;

    @Test
    public void testRecord(){

        List<Record> list = recordService.query()
                .select("count(*) total, DATE_FORMAT( time, '%Y-%m' ) date ")
                .groupBy("date").list();
        List<RecordMap> resultMaps = RecordMap.init();
        for (Record record : list) {
            String date = record.getDate();
            for (RecordMap resultMap : resultMaps) {
                if(Objects.equals(date, resultMap.getDate())){
                    resultMap.setTotal(record.getTotal());
                }
            }
        }
    }

    @Autowired
    private RecordMapper recordMapper;

    @Test
    public void testRecordPage(){
        IPage<Record> recordByCondition = recordMapper.getRecordByCondition(new Page<>(1, 10), new QueryWrapper<Record>().eq("uid", 22));
        for (Record record : recordByCondition.getRecords()) {
            System.out.println(record);
        }
    }

}
