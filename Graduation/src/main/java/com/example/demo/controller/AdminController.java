package com.example.demo.controller;


import com.example.demo.entity.Admin;
import com.example.demo.server.AdminImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(name = "admin", value = {"/admin"})
public class AdminController {

  static {
    System.out.println("进入controller");
  }

  @Autowired
  private AdminImpl adminService;  //自动注入

  @RequestMapping(value = "/login/{username}/{password}")
  public ResponseEntity<Void> login(@PathVariable("username") int username,
      @PathVariable("password") String psd) {
    Admin admin = new Admin();
    admin.setAdminId(username);
    admin.setAdminPsd(psd);
    boolean flag = adminService.login(admin);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }
}
