package com.anatkh.product;

import com.anatkh.product.entity.Attr;
import com.anatkh.product.service.AttrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class ProductTest {

    @Autowired
    private AttrService attrService;

    @Test
    public void test(){
        List<Attr> list1 = attrService.list();
        System.out.println(list1);
    }
}
