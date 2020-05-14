package com.example.demo;

import com.example.demo.dao.AdminMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

@Autowired
private AdminMapper adminMapper;
    @Test
    public void contextLoads() {
        List list=adminMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
