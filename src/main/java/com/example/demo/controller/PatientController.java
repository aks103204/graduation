package com.example.demo.controller;


import com.example.demo.entity.Patient;
import com.example.demo.server.PatientImpl;
import java.io.IOException;
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
@RequestMapping(name = "patient", value = {"/patient"})
public class PatientController {

  @Autowired
  PatientImpl patientService;

  @RequestMapping(value = "/login/{username}/{password}")
  public ResponseEntity<Void> login(@PathVariable("username") int username,
      @PathVariable("password") String psd) {
    Patient patient = new Patient();
    patient.setPatId(username);
    patient.setPatPsd(psd);
    boolean flag = patientService.login(patient);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/patient_Info/{pno}")
  public ResponseEntity<List> patient_Info(@PathVariable("pno") int pno) throws Exception {
    List list = patientService.queryPatient_InfoByPno(pno);
    return new ResponseEntity<List>(list, HttpStatus.OK);
  }

  @RequestMapping(value = "/patient_Update_Info/{pno}/{phone}/{addr}/{qq}")
  public ResponseEntity<Void> patient_Update_Info(@PathVariable("pno") int pno,
      @PathVariable("phone") String phone,
      @PathVariable("addr") String addr) {
    Patient patient = new Patient();
    patient.setPatId(pno);
    patient.setPatPhone(phone);
    patient.setPatAddr(addr);
    boolean flag = patientService.updatePatient_InfoByPno(patient);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/patient_Register/{psd}/{name}/{sex}/{phone}/{age}/{addr}/{marriage}/{nationality}/{birthplace}")
  public ResponseEntity<Void> patient_Register(@PathVariable("psd") String psd,
      @PathVariable("name") String name,
      @PathVariable("sex") String sex,
      @PathVariable("phone") String phone,
      @PathVariable("age") String age,
      @PathVariable("addr") String addr,
      @PathVariable("marriage") String marriage,
      @PathVariable("nationality") String nationality,
      @PathVariable("birthplace") String birthplace) {
    Patient patient = new Patient();
    patient.setPatPsd(psd);
    patient.setPatName(name);
    patient.setPatSex(sex);
    patient.setPatAge(age);
    patient.setPatMarriage(marriage);
    patient.setPatNationality(nationality);
    patient.setPatBirthplace(birthplace);
    patient.setPatPhone(phone);
    patient.setPatAddr(addr);
    boolean flag = patientService.registerPatient(patient);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }


}
