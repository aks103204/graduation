package com.example.demo;

import com.example.demo.tool.RedisUtils;
import javax.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

  @Resource
  private RedisUtils redisUtils;

  /**
   * 插入缓存数据
   */
  @Test
  public void set() {
    redisUtils.set("redis_key", "redis_vale");
  }

  /**
   * 读取缓存数据
   */
  @Test
  public void get() {
    String value = redisUtils.get("redis_key");
    System.out.println(value);
  }

}
