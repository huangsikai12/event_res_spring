package com.huangsikai.eventresspring;

import com.huangsikai.eventresspring.config.JwtConfig;
import com.huangsikai.eventresspring.utils.PasswordEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.util.Arrays;

@SpringBootTest
class EventResSpringApplicationTests {

//    @Autowired
//    JwtConfig jwtConfig;
//   // @Autowired
//    Jedis jedis = new Jedis("u.huangsikai.top",6379);
//    @Test
//    void contextLoads() throws InterruptedException {
////        String token = jwtConfig.createToken("admin");
////        Thread.sleep(6000);
////        System.out.println(token);
////        System.out.println(jwtConfig.getSubject(token));
//        System.out.println(jedis.ping());
//    }

    @Test
    void contextLoads() throws Exception {
//        System.out.println(PasswordEncryptor.encrypt("123456"));
//        System.out.println(PasswordEncryptor.decrypt("8uJPbUmtiFZzqHs8sHmjlA=="));
        FileInputStream fis = new FileInputStream("/Users/baixing/Documents/GitLab/event-res-spring/src/test/java/com/huangsikai/eventresspring/test.webp");
        System.out.println(Arrays.toString(fis.readAllBytes()));
    }

}
