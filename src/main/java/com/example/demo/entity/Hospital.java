package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hospital")
public class Hospital {

  @TableField
  int hosId;

  String hosName;

  String hosNews;

  String hosSitu;

  String hosParty;

  String hosHonor;

  String hosMednews;

  String hosInter;
}
