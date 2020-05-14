package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Contacts;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DoctorMapper extends BaseMapper<Doctor> {

  @Select("select d.*\n"
      + "      from docandpat\n"
      + "      left join  doctorinfo p\n"
      + "      on p.docId = docandpat.docId\n"
      + "      left join patientinfo d\n"
      + "      on d.patId = docandpat.patId\n"
      + "      where p.docId=#{docId,jdbcType=VARCHAR}")
  List<Patient> queryPatientList(@Param("docId") int docId);

  @Select("select d.*\n"
      + "         from doccontacts\n"
      + "         left join  doctorinfo p\n"
      + "         on p.docId = doccontacts.docId1\n"
      + "         left join doctorinfo d\n"
      + "         on d.docId = doccontacts.docId2\n"
      + "         where p.docId=#{docId,jdbcType=VARCHAR}")
  List<Doctor> queryContactsList(int dno);
}
