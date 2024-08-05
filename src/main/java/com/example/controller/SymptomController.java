package com.example.controller;

import com.example.common.Result;
import com.example.entity.Params;
import com.example.entity.Symptom;
import com.example.service.SymptomService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/symptom")
public class SymptomController {
    @Resource
    private SymptomService symptomservice;

    @PostMapping
    public Result add(@RequestBody Symptom symptom){

        if(symptom.getId() == null){
            symptomservice.add(symptom);
        }else{
            symptomservice.edit(symptom);
        }

        return Result.success();
    }
    @GetMapping
    public Result getSymptom(){
        List<Symptom> list= symptomservice.getSymptom();
        return Result.success(list);
    }

    @GetMapping("/search")
    public  Result findBySearch(Params params){
        PageInfo<Symptom> info = symptomservice.findBySearch(params);
        return Result.success(info);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        symptomservice.delete(id);
        return Result.success();
    }

    @GetMapping("/echarts/getchat")
    public Result getDate(Params params){
        List<Symptom> list = symptomservice.findCurve(params);
        List<String>  xAxis = new ArrayList<>();
        List<Integer> yAxis = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Symptom symptom: list){
            xAxis.add(dateFormat.format(symptom.getDate()));
            yAxis.add(symptom.getSeverity());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xAxis",xAxis);
        map.put("yAxis",yAxis);
        return Result.success(map);
    }
    @GetMapping("/echarts/avg")
    public Result getAvg(Params params){
        List<Symptom> list = symptomservice.findAll(params);
        return  Result.success(list);
    }


}
