package org.csu.adminsys.service;

import org.csu.adminsys.entity.User;

import java.util.List;

public interface UserService {
    //查
    public User findUserById(int id);
    public User findUserByUsername(String username);
    public List<User> findAllUsers();
    public List<User> findAdminUser();//查询管理员
    public boolean IsUserExist(int id);
    public void updateUserById(User user);
    public void insertUser(User User);
    public void deleteUserById(int userid);
    public void deleteUserByUsername(String username);
}
