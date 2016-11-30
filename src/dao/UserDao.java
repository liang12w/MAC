package dao;

import entities.UserInfo;
import entities.UserPassword;
import java.util.Date;
import java.security.MessageDigest;
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
  
  public static final String KEY_MD5 = "MD5"; 
  public boolean check(String username, String password) {
      String hql = " SELECT FROM UserPassword u WHERE u.usrPwd = ? WHERE u.usrId = (SELECT user.usrId FROM UserInfo user WHERE user.usrName = ?)";     
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
  public boolean signUp (UserInfo user, UserPassword u) {
	 
      System.out.println("Saving user Messages !");
      System.out.println(user);
      Date now = new Date();
      user.setFirstLogin(now);
      try {
		user.setSecurityId(UserDao.genSid(user.getUsrName()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      getSession().save(user); 
      this.savePassword(user, u);
//    getSession().flush();
      return true;
  }
  public void savePassword(UserInfo user, UserPassword u){
	 u.setUsrId(user.getUsrId());
	 getSession().save(u);
	  
  }
  /** 
   * MD5加密 
   *  
   * @param data 
   * @return 
   * @throws Exception 
   */  
  public static String genSid(String userName) throws Exception {  
	
	  byte[] user =  userName.getBytes();
      MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);  
      md5.update(user);  
      return md5.digest().toString();  
  }  

  public boolean isExist(String name){
     
	  String hql = " SELECT FROM  UserInfo user WHERE user.usrName = ?)";     
      List list = getSession().createQuery(hql).list();
      if (list.isEmpty()==true) {
           return false;
      }
      return true;
  }
}