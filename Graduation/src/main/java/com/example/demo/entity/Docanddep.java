package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("docanddep")
public class Docanddep {

  @TableField
  int depId;

  int docId;
}
