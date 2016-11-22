package dao;

import entities.UserInfo;
import entities.UserPassword;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDao extends BaseDao {
  
  
  public boolean check(UserInfo user, UserPassword u) {
      String hql = " SELECT FROM UserPassword u WHERE u.userpassword = ? WHERE u.userid = (SELECT user.userid FROM UserInfo user WHERE user.username = ?)";     
      List list = getSession().createQuery(hql).setString(0, user.getUsrName()).setString(1, u.getUsrPwd()).list();
      if (list.isEmpty()==true) {
           return false;
      }
      return true;
  }
  public boolean checkSid(UserInfo user){
	  String hql = "FROM UserInfo e WHERE E.SECURITY_ID = ?";
	  List list = getSession().createQuery(hql).setString(0,user.getSecurityId()).list();
	  if(list.isEmpty()==true){
		  return false;
	  }
	  return true;
  }
  public boolean signin(UserInfo user, UserPassword u) {
	 
      System.out.println("Saving user Messages !");
      System.out.println(user);
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