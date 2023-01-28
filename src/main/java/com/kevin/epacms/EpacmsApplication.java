package com.kevin.epacms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 主启动类
 *
 * @author kevin
 * @since 2022/11/11
 */
@SpringBootApplication
@EnableWebMvc
public class EpacmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpacmsApplication.class, args);
    }

}
