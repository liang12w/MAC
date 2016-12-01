package dao;

import java.util.Date;
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
			user = (UserInfo) list.get(0);
			moment.setUserInfo(user);
			moment.setMotContent(content);
			moment.setMotSentTime(new Date());
			getSession().save(moment);
			return true;
		}
		return false;
	
	}
	public boolean saveUrl(String url,Moments moment){
		
		moment.setMotGifUri(url);
		getSession().save(moment);
		return true;
		
	}
	public void getKeyWord(){
		
	}

	public String genGif(String keyword){
		String url = null;
		url = HttpUtils.getHttpResult("http://api.giphy.com/v1/gifs/search?q=funny+cat&api_key=dc6zaTOxFJmzC");
		return url;
	}

}
