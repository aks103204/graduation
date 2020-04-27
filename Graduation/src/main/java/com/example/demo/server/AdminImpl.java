package com.example.demo.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.AdminMapper;
import com.example.demo.entity.Admin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminImpl {

  @Autowired
  AdminMapper adminMapper;


  public boolean login(Admin admin) {
    boolean flag = false;
    QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
    queryWrapper.eq("adminId", admin.getAdminId()).eq("adminPsd", admin.getAdminPsd());
    List<Admin> list = adminMapper.selectList(queryWrapper);
    if (list.size() > 0) {
      flag = true;
    }
    return flag;
  }
}
