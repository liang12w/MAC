package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;

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
			Date date = new Date();
			moment.setMotSentTime(date);
			Date vdate = MomentDao.getVanishTime(date);
			moment.setMotVanishTime(vdate);
			getSession().save(moment);
			return true;
		}
		return false;
	
	}
	public static Date getVanishTime(Date starttime){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(starttime);
		int day = c1.get(Calendar.DAY_OF_MONTH);
		c1.set(Calendar.DATE,day + 3);
		return c1.getTime();
	}
	public boolean saveUrl(String url,Moments moment){
		
		moment.setMotGifUri(url);
		getSession().save(moment);
		return true;
		
	}
	public void getKeyWord(){
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("cf12a4426504285e2a30fcebd1933f4c133141a7");
		
	}

	public String genGif(String keyword){
		String url = null;
		url = HttpUtils.getHttpResult("http://api.giphy.com/v1/gifs/search?q=funny+cat&api_key=dc6zaTOxFJmzC");
		return url;
	}
	public List showAllMoment(){
		Date now = new Date();
		String hql = "select motContent, motGifUri,userInfo from Moments where rownum > (select count(*) - 15 from table)";
		List list = getSession().createQuery(hql).list();
		for(int i = 0; i<list.size();i++){
			moment = (Moments) list.get(i);
			if(now.after(moment.getMotVanishTime())){
				list.remove(i);
			}
		}
		
		return list;
	}

}
