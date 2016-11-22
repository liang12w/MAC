package service;

import dao.UserDao;
import entities.UserInfo;
import entities.UserPassword;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author a1611
 */
@Service
@Transactional
public class UserService {
    @Autowired
    public UserDao userDao;
    
    public boolean check(UserInfo USER,UserPassword U){
        return userDao.check(USER,U);
    } 
    public boolean checkSid(UserInfo Sid){
    	return userDao.checkSid(Sid);
    }
    public boolean isExist(String name){
    	return userDao.isExist(name);
    }
    public boolean signin(UserInfo user, UserPassword u){
    	return userDao.signin(user,u);
    }
}

