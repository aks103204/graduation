package com.example.demo.server;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.dao.DoctorMapper;
import com.example.demo.entity.Contacts;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Record;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("doctorService")
public class DoctorImpl {

  @Autowired
  DoctorMapper doctorMapper;

  public Doctor queryDoctor_InfoByDno(int dno) {
    QueryWrapper<Doctor> queryWrapper = new QueryWrapper<Doctor>();
    queryWrapper.eq("docId", dno);
    Doctor doctor = doctorMapper.selectOne(queryWrapper);
    return doctor;
  }

  public boolean login(Doctor doctor) {
    boolean flag = false;
    QueryWrapper<Doctor> queryWrapper = new QueryWrapper<Doctor>();
    queryWrapper.eq("docId", doctor.getDocId()).eq("docPsd", doctor.getDocPsd());
    List<Doctor> list = doctorMapper.selectList(queryWrapper);
    if (list.size() > 0) {
      flag = true;
    }
    return flag;
  }

  public boolean updateDoctor_InfoByDno(Doctor doctor) {
    UpdateWrapper<Doctor> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("docId", doctor.getDocId());
    int rows = doctorMapper.update(doctor, updateWrapper);
    if (rows > 0) {
      return true;
    } else {
      return false;
    }
  }

  public List<Patient> queryDoctor_PatientByDno(int dno) {
    List<Patient> list = doctorMapper.queryPatientList(dno);
    return list;
  }

  public List<Doctor> queryDoctor_FullInfoByDno(int dno) {
    List<Doctor> list = doctorMapper.queryContactsList(dno);
    return list;
  }
}
