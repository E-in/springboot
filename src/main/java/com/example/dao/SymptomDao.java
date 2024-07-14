package com.example.dao;


import com.example.entity.Params;
import com.example.entity.Symptom;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SymptomDao extends Mapper<Symptom> {
    List<Symptom> findBySearch(@Param("params") Params params);


}


