package com.example.demo.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.dao.OutRecordMapper;
import com.example.demo.dao.PatientMapper;
import com.example.demo.dao.RecordMapper;
import com.example.demo.entity.Department;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DoctorFull;
import com.example.demo.entity.OutRecord;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Record;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("patientService")
public class PatientImpl {

  @Autowired
  PatientMapper patientMapper;
  @Autowired
  RecordMapper recordMapper;
  @Autowired
  OutRecordMapper outRecordMapper;

  public boolean login(Patient patient) {
    boolean flag = false;
    QueryWrapper<Patient> queryWrapper = new QueryWrapper<Patient>();
    queryWrapper.eq("patId", patient.getPatId()).eq("patPsd", patient.getPatPsd());
    List<Patient> list = patientMapper.selectList(queryWrapper);
    if (list.size() > 0) {
      flag = true;
    }
    return flag;
  }

//  public Patient queryPatient_InfoByPno(int pno) {
//    QueryWrapper<Patient> queryWrapper = new QueryWrapper<Patient>();
//    queryWrapper.eq("patId",pno);
//    Patient patient = patientMapper.selectOne(queryWrapper);
//    return patient;
//  }

  public List queryPatient_InfoByPno(int pno) throws Exception {
    QueryWrapper<Patient> queryWrapper = new QueryWrapper<Patient>();
    queryWrapper.eq("patId", pno);
    Patient patient = patientMapper.selectOne(queryWrapper);
    QueryWrapper<Record> queryWrapper1 = new QueryWrapper<Record>();
    queryWrapper.eq("recId", patient.getPatrecId());
    Record record = recordMapper.selectOne(queryWrapper1);
    QueryWrapper<OutRecord> queryWrapper2 = new QueryWrapper<OutRecord>();
    queryWrapper.eq("outId", patient.getPatoutId());
    OutRecord outRecord = outRecordMapper.selectOne(queryWrapper2);
    Doctor doctor = patientMapper.selectDoctorById(pno);
    Department department = patientMapper.selectOutdepById(patient.getPatoutId());
    List list = new ArrayList();
    list.add(patient);
    list.add(record);
    list.add(doctor);
    list.add(outRecord);
    list.add(department);
    return list;
  }

  public boolean updatePatient_InfoByPno(Patient patient) {
    UpdateWrapper<Patient> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("patId", patient.getPatId());
    int rows = patientMapper.update(patient, updateWrapper);
    if (rows > 0) {
      return true;
    } else {
      return false;
    }
  }

  public boolean registerPatient(Patient patient) {
    Boolean flag;
    int rows = patientMapper.insert(patient);
    if (rows == 1) {
      flag = true;
    } else {
      flag = false;
    }
    return flag;
  }

  public List<Patient> queryAllpatient() {
    List<Patient> list = patientMapper.selectAllpatient();
    return list;
  }

  public List<DoctorFull> queryAlldoctor() {
    List<DoctorFull> list = new ArrayList();
    List<Doctor> doctors = patientMapper.selectAlldoctor();
    for (Doctor d : doctors) {
      DoctorFull doctorFull = new DoctorFull();
      Department department = patientMapper.selectDepById(d.getDocId());
      doctorFull.setDoctor(d);
      doctorFull.setDepartment(department);
      list.add(doctorFull);
    }
    return list;
  }

  public DoctorFull queryDoctor_DetialsByDno(int dno) {
    Doctor doctor = patientMapper.selectDoctor_DetialsByDno(dno);
    Department department = patientMapper.selectDepById(doctor.getDocId());
    DoctorFull doctorFull = new DoctorFull();
    doctorFull.setDoctor(doctor);
    doctorFull.setDepartment(department);
    return doctorFull;
  }
}
