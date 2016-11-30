package dao;

import entities.UserInfo;
import entities.UserPassword;
import java.util.Date;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDao extends BaseDao {
  
  
  public boolean check(String username, String password) {
      String hql = " SELECT FROM UserPassword u WHERE u.userpassword = ? WHERE u.userid = (SELECT user.userid FROM UserInfo user WHERE user.username = ?)";     
      List list = getSession().createQuery(hql).setString(0, username).setString(1, password).list();
      if (list.isEmpty()==true) {
           return false;
      }else{
    	  UserInfo user = (UserInfo) list.get(0);
		  user.setLastLogin(new Date());
		  getSession().save(user);
		  return true;
      }
  }
  public boolean checkSid(String sid){
	  String hql = "FROM UserInfo e WHERE e.securityId = ?";
	  List list = getSession().createQuery(hql).setString(0,sid).list();
	  if(list.isEmpty()==true){
		  return false;
	  }else{
		  UserInfo user = (UserInfo) list.get(0);
		  user.setLastLogin(new Date());
		  getSession().save(user);
		  return true;
	  }

  }
  public boolean signin(UserInfo user, UserPassword u) {
	 
      System.out.println("Saving user Messages !");
      System.out.println(user);
      Date now = new Date();
      user.setFirstLogin(now);
      getSession().save(user); 
      this.savePassword(user, u);
//    getSession().flush();
      return true;
  }
  public void savePassword(UserInfo user, UserPassword u){
	 u.setUsrId(user.getUsrId());
	 getSession().save(u);
	  
  }
  
  public boolean isExist(String name){
     
	  String hql = " SELECT FROM  UserInfo user WHERE user.username = ?)";     
      List list = getSession().createQuery(hql).list();
      if (list.isEmpty()==true) {
           return false;
      }
      return true;
  }
}