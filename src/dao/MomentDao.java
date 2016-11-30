package dao;

import java.util.List;

import entities.Moments;
import entities.UserInfo;
import utils.HttpUtils;

public class MomentDao extends BaseDao {

	UserInfo user = new UserInfo();
	Moments moment = new Moments();
	public boolean saveContent(String content, int id) {
		String hql = "FROM UserInfo e WHERE e.usrId= ?";
		List list = getSession().createQuery(hql).setInteger(0, id).list();
		if(!list.isEmpty()){
//			moment = (Moments)list.get(0).setMotContent(content);
		}
		return true;
	}

	public void genGif(){
		String keyword = null;
		keyword = HttpUtils.getHttpResult("http://api.giphy.com/v1/gifs/search?q=funny+cat&api_key=dc6zaTOxFJmzC");
	}

}
