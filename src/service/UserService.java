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
    public void refreshTime(int id) {
         userDao.refreshTime(id);
    }
    
    public UserInfo checkProfile(int id){
    	return userDao.checkProfile(id);
    }
    public boolean check(String username,String password){
        return userDao.check(username,password);
    } 
    public int checkSid(String Sid){
    	return userDao.checkSid(Sid);
    }
    public boolean isExist(String name){
    	return userDao.isExist(name);
    }
    public boolean signUp(UserInfo user){
    	return userDao.signUp(user);
    }
    public boolean savePassword(UserInfo user,UserPassword u){
    	return userDao.savePassword(user, u);
    }
    public static String genSid(String userName){
    	return UserDao.genSid(userName);
    }
}

