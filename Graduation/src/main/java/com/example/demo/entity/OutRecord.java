package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("outrecord")
public class OutRecord {

  @TableField
  int outId;

  int patId;

  int docId;

  int depId;

  String outrecord;

  String outtime;

  String outtemperature;

  String outpulse;

  String outlongtimemed;

  String outbreath;

  String outbloodpress;
}