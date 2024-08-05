package com.example.service;

import com.example.dao.SymptomDao;
import com.example.entity.Params;
import com.example.entity.Symptom;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SymptomService {
    @Resource
    private SymptomDao symptomdao;

    public void add(Symptom symptom){

        if(symptom.getPattern() ==null||"".equals(symptom.getPattern())){
            throw new CustomException("Pattern Cannot Be Empty");
        }
        if(symptom.getSeverity() ==null||"".equals(symptom.getSeverity())){
            throw new CustomException("Severity Cannot Be Empty");
        }
        if(symptom.getLocation() ==null||"".equals(symptom.getLocation())){
            throw new CustomException("Location Cannot Be Empty");
        }
        if(symptom.getTriggers() ==null||"".equals(symptom.getTriggers())){
            throw new CustomException("Triggers Cannot Be Empty");
        }

        symptomdao.insertSelective(symptom);
    }
    public void edit(Symptom symptom){
        if(symptom.getPattern() ==null||"".equals(symptom.getPattern())){
            throw new CustomException("Pattern Cannot Be Empty");
        }
        if(symptom.getSeverity() ==null||"".equals(symptom.getSeverity())){
            throw new CustomException("Severity Cannot Be Empty");
        }
        if(symptom.getLocation() ==null||"".equals(symptom.getLocation())){
            throw new CustomException("Location Cannot Be Empty");
        }
        if(symptom.getTriggers() ==null||"".equals(symptom.getTriggers())){
            throw new CustomException("Trigger Cannot Be Empty");
        }
        symptomdao.updateByPrimaryKeySelective(symptom);
    }
    public void delete(Integer id){
        symptomdao.deleteByPrimaryKey(id);
    }
    public List<Symptom> getSymptom(){

        return symptomdao.selectAll();
    }
    public List<Symptom> findAll(Params params){
        List<Symptom> list = symptomdao.findAll(params);
        return list;
    }
    public PageInfo<Symptom> findBySearch(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Symptom> list = symptomdao.findBySearch(params);
        return PageInfo.of(list);
    }
    public List<Symptom> findCurve(Params params){
        List<Symptom> list = symptomdao.findCurve(params);
        return list;
    }
}
