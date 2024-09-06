package com.huangsikai.eventresspring;

import com.huangsikai.eventresspring.config.JwtConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventResSpringApplicationTests {

    @Autowired
    JwtConfig jwtConfig;
    @Test
    void contextLoads() throws InterruptedException {
        String token = jwtConfig.createToken("admin");
        Thread.sleep(6000);
        System.out.println(token);
        System.out.println(jwtConfig.getSubject(token));
    }

}
