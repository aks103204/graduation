package com.example.demo.controller;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DoctorFull;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.Patient;
import com.example.demo.server.AdminImpl;
import com.example.demo.server.DoctorImpl;
import com.example.demo.server.PatientImpl;
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
@RequestMapping(name = "admin", value = {"/admin"})
public class AdminController {

  static {
    System.out.println("进入controller");
  }

  @Autowired
  private AdminImpl adminService;  //自动注入
  @Autowired
  private PatientImpl patientService;
  @Autowired
  private DoctorImpl doctorService;

  @RequestMapping(value = "/login/{username}/{password}")
  public ResponseEntity<Void> login(@PathVariable("username") int username,
      @PathVariable("password") String psd) {
    Admin admin = new Admin();
    admin.setAdminId(username);
    admin.setAdminPsd(psd);
    boolean flag = adminService.login(admin);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/hospital_Info")
  public ResponseEntity<Hospital> hospital_Info() {
    Hospital hospital = adminService.queryHospital_Info();
    return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
  }

  @RequestMapping(value = "/admin_Info/{ano}")
  public ResponseEntity<Admin> admin_Info(@PathVariable("ano") int ano) {
    Admin admin = adminService.queryAdmin_InfoByAno(ano);
    return new ResponseEntity<Admin>(admin, HttpStatus.OK);
  }

  @RequestMapping(value = "/patientlist")
  public ResponseEntity<List<Patient>> patientlist() {
    List<Patient> list = patientService.queryAllpatient();
    return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
  }

  @RequestMapping(value = "/doctorlist")
  public ResponseEntity<List<DoctorFull>> doctorlist() {
    List<DoctorFull> list = patientService.queryAlldoctor();
    return new ResponseEntity<List<DoctorFull>>(list, HttpStatus.OK);
  }

  @RequestMapping(value = "/doctor_Detials_Info/{dno}")
  public ResponseEntity<DoctorFull> doctor_Detials_Info(@PathVariable("dno") int dno) {
    DoctorFull doctorFull = patientService.queryDoctor_DetialsByDno(dno);
    return new ResponseEntity<DoctorFull>(doctorFull, HttpStatus.OK);
  }

  @RequestMapping(value = "/admin_doctor_Update_Info/{dno}/{name}/{sex}/{age}/{phone}/{addr}/{proname}")
  public ResponseEntity<Void> admin_doctor_Update_Info(@PathVariable("dno") int dno,
      @PathVariable("name") String name,
      @PathVariable("sex") String sex,
      @PathVariable("age") String age,
      @PathVariable("phone") String phone,
      @PathVariable("addr") String addr,
      @PathVariable("proname") String proname) {
    int proid = adminService.queryDepByName(proname);
    Doctor doctor = new Doctor();
    doctor.setDocId(dno);
    doctor.setDocName(name);
    doctor.setDocSex(sex);
    doctor.setDocAge(age);
    doctor.setDocPhone(phone);
    doctor.setDocAddr(addr);
    boolean flag = adminService.updateDoctor_InfoByDno(doctor, proid);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/admin_Update_Info/{ano}/{phone}")
  public ResponseEntity<Void> admin_Update_Info(@PathVariable("ano") int ano,
      @PathVariable("phone") String phone) {
    Admin admin = new Admin();
    admin.setAdminId(ano);
    admin.setAdminPhone(phone);
    boolean flag = adminService.updateAdmin_InfoByAno(admin);
    if (flag) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }
}
