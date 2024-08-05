package com.example.service;

import com.example.common.JwtTokenUtils;
import com.example.dao.SymptomDao;
import com.example.dao.UserDao;
import com.example.entity.Params;
import com.example.entity.Symptom;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userdao;

    @Resource
    private SymptomDao symptomdao;

    public void add(User user){
        User newuser = userdao.findById(user.getId());
        if(newuser !=null ){
            throw new CustomException("ID already exist");
        }
        if(user.getId() ==null||"".equals(user.getId())){
            throw new CustomException("Id Cannot Be Empty");
        }
        if(user.getName() ==null||"".equals(user.getName())){
            throw new CustomException("Name Cannot Be Empty");
        }
        if(user.getPassword() ==null||"".equals(user.getPassword())){
            throw new CustomException("Password Cannot Be Empty");
        }
        if(user.getAge() ==null||"".equals(user.getAge())){
            throw new CustomException("Age Cannot Be Empty");
        }
        if(user.getGender() ==null||"".equals(user.getGender())){
            throw new CustomException("Gender Cannot Be Empty");
        }
        if(user.getPhone() ==null||"".equals(user.getPhone())){
            throw new CustomException("Phone Cannot Be Empty");
        }

        userdao.insertSelective(user);
    }
    public void edit(User user){
        userdao.updateByPrimaryKeySelective(user);
    }
    public void delete(Integer id){
        userdao.deleteByPrimaryKey(id);
        Example example = new Example (Symptom.class);
        example.createCriteria().andEqualTo("user_id",id);
        symptomdao.deleteByExample(example);
    }
    public User login(User user){
        if(user.getId() ==null||"".equals(user.getId())){
            throw new CustomException("Id Cannot Be Empty");
        }
        if(user.getPassword() ==null||"".equals(user.getPassword())){
            throw new CustomException("Password Cannot Be Empty");
        }
        User loginuser = userdao.findByIdAndPassword(user.getId(),user.getPassword());
        if(loginuser == null){
            throw new CustomException("Id or Password Incorrect");
        }
        String token =JwtTokenUtils.genToken(user.getId(), user.getPassword());
        loginuser.setToken(token);
        return loginuser;

    }
    public List<User> getuser(){

        return userdao.selectAll();
    }
    public PageInfo<User> findBySearch(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<User> list = userdao.findBySearch(params);
        return PageInfo.of(list);

    }
    public User findById(String Id){
        return userdao.selectByPrimaryKey(Id);
    }
}
