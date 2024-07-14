package com.example.controller;

import com.example.common.Result;
import com.example.entity.Params;
import com.example.entity.Symptom;
import com.example.service.SymptomService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

}
