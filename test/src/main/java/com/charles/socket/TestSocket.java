package com.charles.socket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/helloSocket")
public class TestSocket {

  /***
   * 当建立链接时，调用的方法.
   * @param session
   */
  @OnOpen
  public void open(Session session) {
    System.out.println("开始建立了链接...");
    System.out.println("当前session的id是：" + session.getId());
  }
}