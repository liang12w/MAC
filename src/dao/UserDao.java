package dao;

import entities.UserInfo;
import entities.UserPassword;

import java.util.List;

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
  
//  public int isExist(String name,String password){
//      //int state = 0 ;        //初始化状态变量
////      Session session = getSession();
//      try{
//          Query query = getSession().createQuery("from UserInfo u where u.nickname = ?");
//           query.setString(0, name);
//         List list = query.list();
//          if(null == list || 0 == list.size()){
//              return -1 ;     //用户名不存在
//          }
//          Query query2 = session.createQuery("from User u where u.username = ? and u.password = ?");
//          query2.setString(0, name);
//          query2.setString(1, password);
//          List list2 = query.list();
//          if(null == list2){
//              return -2 ;        //密码不正确
//          }
//          Iterator it = list.iterator();
//          User user = (User)it.next();
//          return user.getId();    //验证成功,取ID值
//          
//      }catch(Exception e){
//          System.out.println("UserDao.isExist()方法发生异常:");
//          e.printStackTrace();
//          return 0;    //异常时返回0
//      }finally{
//          util.closeSession(session);
//      }
//  }

}

