package com.example.service;

import com.example.dao.SymptomDao;
import com.example.entity.Params;
import com.example.entity.Symptom;
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
        symptomdao.insertSelective(symptom);
    }
    public void edit(Symptom symptom){
        symptomdao.updateByPrimaryKeySelective(symptom);
    }
    public void delete(Integer id){
        symptomdao.deleteByPrimaryKey(id);
    }
    public List<Symptom> getSymptom(){

        return symptomdao.selectAll();
    }
    public PageInfo<Symptom> findBySearch(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Symptom> list = symptomdao.findBySearch(params);
        return PageInfo.of(list);
    }
}
