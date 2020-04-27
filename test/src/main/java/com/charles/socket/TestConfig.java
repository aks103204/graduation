package com.charles.socket;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class TestConfig implements ServerApplicationConfig{

  // 这个方法是使用注解进行开发
  @Override
  public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> arg0) {
    // TODO 我们使用注解进行开发，简单。

    // 这个方法中可以进行过滤筛选Socket，但我们现在什么都不操作，直接返回arg0 ，21行：return arg0
    System.out.println("*******************************************");
    System.out.println("WebSocket初始的个数：" + arg0.size());
    System.out.println("*******************************************");

    return arg0;
  }

  // 这个方法是使用配置文件进行开发
  @Override
  public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
    // TODO Auto-generated method stub
    return null;
  }
}