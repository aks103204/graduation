package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("medicalrecord")
public class Record {

  @TableField
  int recId;

  int patId;

  int docId;

  String record;

  String infectious;

  String nowdiseases;

  String beforediseases;

  String personalhistory;

  String time;

  String hospital;

  String temperature;

  String pulse;

  String longtimemed;

  String breath;

  String bloodpress;

  String diseasename;

  String operation;
}
