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
}
