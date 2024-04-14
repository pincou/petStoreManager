package org.csu.adminsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.adminsys.Mappers.UserMapper;
import org.csu.adminsys.entity.User;
import org.csu.adminsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return userMapper.selectOne(queryWrapper);
    }

    public User findUserByUsername(String username){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.selectOne(queryWrapper);
    }
    public List<User> findAllUsers(){
        return userMapper.selectList(null);
    }

    public List<User> findAdminUser(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("isAdmin",1);
        return userMapper.selectList(queryWrapper);
    }
    @Override
    public boolean IsUserExist(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = userMapper.selectOne(queryWrapper);
        return user!=null;
    }

    public void updateUserById(User user){
        userMapper.updateById(user);
    }
    public void insertUser(User user){
        userMapper.insert(user);
    }

    public void deleteUserById(int userid){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",userid);
        userMapper.delete(queryWrapper);


    }
    public void deleteUserByUsername(String username){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        userMapper.delete(queryWrapper);

    }
}
