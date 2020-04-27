package com.example.demo.controller;


import com.example.demo.entity.Contacts;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Record;
import com.example.demo.server.DoctorImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(name = "doctor", value = {"/doctor"})
public class DoctorController {

  @Autowired
  DoctorImpl doctorService;

  @RequestMapping(value = "/login/{username}/{password}")
  public ResponseEntity<Void> login(@PathVariable("username") int username,
      @PathVariable("password") String psd) {
    Doctor doctor = new Doctor();
    doctor.setDocId(username);
    doctor.setDocPsd(psd);
    boolean flag = doctorService.login(doctor);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/doctor_Info/{dno}")
  public ResponseEntity<Doctor> doctor_Info(@PathVariable("dno") int dno) {
    Doctor doctor = doctorService.queryDoctor_InfoByDno(dno);
    return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
  }

  @RequestMapping(value = "/doctor_Full/{dno}")
  public ResponseEntity<List<Doctor>> doctor_Full(@PathVariable("dno") int dno) {
    List<Doctor> list = doctorService.queryDoctor_FullInfoByDno(dno);//包含联系人
    return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
  }

  @RequestMapping(value = "/patientlist/{dno}")
  public ResponseEntity<List<Patient>> patientlist(@PathVariable("dno") int dno) {
    List<Patient> list = doctorService.queryDoctor_PatientByDno(dno);
    return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
  }

  @RequestMapping(value = "/doctor_Update_Info/{dno}/{phone}/{addr}/{qq}")
  public ResponseEntity<Void> doctor_Update_Info(@PathVariable("dno") int dno,
      @PathVariable("phone") String phone,
      @PathVariable("addr") String addr) {
    Doctor doctor = new Doctor();
    doctor.setDocId(dno);
    doctor.setDocPhone(phone);
    doctor.setDocAddr(addr);
    boolean flag = doctorService.updateDoctor_InfoByDno(doctor);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }

}
