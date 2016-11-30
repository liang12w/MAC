package dao;

import java.util.List;

import entities.UserInfo;

public class MomentDao extends BaseDao {

	UserInfo user = new UserInfo();
	
	public boolean saveContent(String content, int id) {
		String hql = "FROM UserInfo e WHERE e.usrId= ?";
		List list = getSession().createQuery(hql).setLong(0, id).list();
		return true;
	}

}
