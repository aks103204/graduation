package com.example.demo.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.dao.AdminMapper;
import com.example.demo.dao.DepartmentMapper;
import com.example.demo.dao.DocanddepMapper;
import com.example.demo.dao.DoctorMapper;
import com.example.demo.dao.HospitalMapper;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Department;
import com.example.demo.entity.Docanddep;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Hospital;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminImpl {

  @Autowired
  AdminMapper adminMapper;
  @Autowired
  HospitalMapper hospitalMapper;
  @Autowired
  DepartmentMapper departmentMapper;
  @Autowired
  DoctorMapper doctorMapper;
  @Autowired
  DocanddepMapper docanddepMapper;
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

  public Hospital queryHospital_Info() {
    QueryWrapper<Hospital> queryWrapper = new QueryWrapper<Hospital>();
    queryWrapper.eq("hosId", 1);
    return hospitalMapper.selectOne(queryWrapper);
  }

  public Admin queryAdmin_InfoByAno(int ano) {
    QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
    queryWrapper.eq("adminId", ano);
    Admin admin = adminMapper.selectOne(queryWrapper);
    return admin;
  }

  public boolean updateAdmin_InfoByAno(Admin admin) {
    UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<Admin>();
    updateWrapper.eq("adminId", admin.getAdminId());
    int rows = adminMapper.update(admin, updateWrapper);
    if (rows > 0) {
      return true;
    } else {
      return false;
    }
  }

  public int queryDepByName(String proname) {
    QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("depName", proname);
    Department department = departmentMapper.selectOne(queryWrapper);
    return department.getDepId();
  }

  public boolean updateDoctor_InfoByDno(Doctor doctor, int proid) {
    UpdateWrapper<Doctor> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("docId", doctor.getDocId());
    int rows = doctorMapper.update(doctor, updateWrapper);
    UpdateWrapper<Docanddep> updateWrapper1 = new UpdateWrapper<>();
    updateWrapper1.eq("docId", doctor.getDocId());
    Docanddep docanddep = new Docanddep();
    docanddep.setDocId(doctor.getDocId());
    docanddep.setDepId(proid);
    int rows1 = docanddepMapper.update(docanddep, updateWrapper1);
    if (rows > 0 && rows1 > 0) {
      return true;
    } else {
      return false;
    }
  }
}
