package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.Department;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PatientMapper extends BaseMapper<Patient> {

  @Select(" select p.*,d.*\n"
      + "     from docandpat\n"
      + "      left join  patientinfo p\n"
      + "     on p.patId = docandpat.patId\n"
      + "      left join doctorinfo d\n"
      + "      on d.docId = docandpat.docId\n"
      + "     where p.patId=#{patId,jdbcType=VARCHAR}")
  Doctor selectDoctorById(@Param("patId") int patId);

  @Select(" select *\n"
      + "     from department\n"
      + "     where( department.depId=\n"
      + "     (select depId from outrecord\n"
      + "     left join patientinfo p\n"
      + "     on outrecord.outId=p.patoutId\n"
      + "     where p.patoutId=#{patoutId,jdbcType=VARCHAR}\n"
      + "     )\n"
      + "     )")
  Department selectOutdepById(@Param("patoutId") int patoutId);

  @Select("select * from patientinfo")
  List<Patient> selectAllpatient();

  @Select("select * from doctorinfo")
  List<Doctor> selectAlldoctor();

  @Select(" select *\n"
      + "     from department\n"
      + "     where( department.depId=\n"
      + "     (select depId from docanddep\n"
      + "     left join doctorinfo p\n"
      + "     on docanddep.docId=p.docId\n"
      + "     where p.docId=#{docId,jdbcType=VARCHAR}\n"
      + "     )\n"
      + "     )")
  Department selectDepById(@Param("docId") int docId);

  @Select("select * from doctorinfo where docId=#{docId,jdbcType=VARCHAR}")
  Doctor selectDoctor_DetialsByDno(@Param("docId") int docId);
}
