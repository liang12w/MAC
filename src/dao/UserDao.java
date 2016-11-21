package dao;

import entities.UserPassword;
//import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDao extends BaseDao {
  
  
  public boolean check(User user) {
      String hql = "FROM User e WHERE e.username = ? AND e.password = ?";
      List list = getSession().createQuery(hql).setString(0, user.getUsername()).setString(1, user.getPassword()).list();
      if (list.isEmpty()==true) {
          return false;
      }
      return true;
  }

}

