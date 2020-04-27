package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Blob;
import lombok.Data;

@Data
@TableName("patientinfo")
public class Patient {

  @TableField
  int patId;

  String patPsd;

  String patName;

  Blob patPic;

  String patNationality;

  String patMarriage;

  String patSex;

  String patPhone;

  String patAge;

  String patAddr;

  String patBirthplace;

  int patrecId;

  int patoutId;
}
