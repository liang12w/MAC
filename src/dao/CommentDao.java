package dao;

import org.springframework.stereotype.Repository;

import entities.Comments;
import entities.Moments;
import entities.UserInfo;

@Repository("commentDao")

public class CommentDao extends BaseDao{
	
	UserInfo user = new UserInfo();
	Comments comment = new Comments();
	
	public boolean addComments(String content, int motId){
		String hql = "FROM Moments e WHERE e.motId = ?";
		list = getSession().createQuery(hql).setInteger(0, motId).list();
		if (!list.isEmpty()) {
			Moments moment = (Moments) list.get(0);
			user = (UserInfo) list.get(0);
			comment.setUserInfo(user);
			comment.setComtContent(content);
			Date date = new Date();
			comment.setComtTime(date);			
			moment.setMotCommentNum(moment.getMotCommentNum()+1);
			getSession().save(moment);
			return true;
		} else
			return false;
	}
	
	public List showAllComments(int motId){
		String hql = "select comtContent, comtTime, userInfo FROM Moments e WHERE e.motId = ?";
		List list = getSession().createQuery(hql).list();
		for(int i = 0; i < list.size(); i++){
			comment = (Comments) list.get(i);
		}
	}
	
	public boolean deleteComments(int motId, int comtId){
		String hql = "select comtId, comtContent, comtTime, userInfo FROM Moments e WHERE e.motId = ?";
		list = getSession().createQuery(hql).setInteger(0, motId).list();
		if (!list.isEmpty()) {
			Moments moment = (Moments) list.get(0);
			moment.setMotCommentNum(moment.getMotLikeNum() - 1);
			getSession().save(moment);
			return true;
		} else
			return false;
	}
	
	public boolean modifyComments(int motId, int comtId, String content){
		String hql = "select comtId, comtContent, comtTime, userInfo FROM Moments e WHERE e.motId = ?";
		list = getSession().createQuery(hql).setInteger(0, motId).list();
		if(!list.isEmpty()){
			comment.setComtContent(content);
			Date date = new Date();
			comment.setComtTime(date);
			getSession().save(moment);
		return true;
		} else
			return false;
	}
}
