package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("doccontacts")
public class Contacts {

  int conId;

  int docId1;

  int docId2;

  String lastcontime;
}
