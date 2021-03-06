package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.MomentController;
import entities.Moments;
import entities.UserInfo;
import net.sf.json.util.JSONTokener;
import utils.HttpUtils;
@Repository("momentDao")
public class MomentDao extends BaseDao {
	UserInfo user = new UserInfo();
	Moments moment = new Moments();

	public boolean saveContent(String content, int id, String url) {
		MomentDao momentdao = new MomentDao();
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
			moment.setMotGifUri(url);
			moment.setMotLikeNum(0);
			moment.setMotCommentNum(0);
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
	

	
	public List showAllMoment(int id){
		System.out.println(id);
		Date now = new Date();
		String hql = "From Moments e order by e.motSentTime desc";
		List list = getSession().createQuery(hql).setFirstResult(0)
                .setMaxResults(10).list();
		for(int i = 0; i<list.size();i++){
			moment = (Moments) list.get(i);
			if(now.after(moment.getMotVanishTime())){
				list.remove(i);
			}
		}
		return list;
	}
	public List showOwnMoment(int id){
		Date now = new Date();
		String hql = " from Moments e where e.userInfo.usrId= ? order by e.motSentTime desc";
		List list = getSession().createQuery(hql).setInteger(0, id).setFirstResult(0)
                .setMaxResults(10).list();
		for(int i = 0; i<list.size();i++){
			moment = (Moments) list.get(i);
			if(now.after(moment.getMotVanishTime())){
				list.remove(i);
			}
		}
		return list;
	}
	
}