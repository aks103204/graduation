package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("doctorinfo")
public class Doctor {

  @TableField
  int docId;

  String docPsd;

  String docName;

  String docSex;

  String docPhone;

  String docAge;

  String docAddr;

  String docProId;
}
