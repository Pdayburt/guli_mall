package com.anatkh.product;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {
    @Test
    void test1(){
        Logger logger = LoggerFactory.getLogger("cn.xuchao");
        String name = "Java";
        logger.error("hello {}",name);
    }
}
