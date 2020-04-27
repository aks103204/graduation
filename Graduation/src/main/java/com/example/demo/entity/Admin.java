package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("admininfo")
public class Admin {

  @TableField
  int adminId;

  String adminPsd;

  String adminName;

  String adminPhone;

  String adminQq;

}
