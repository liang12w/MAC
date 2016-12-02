package dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import entities.Comments;
import entities.Moments;
import entities.UserInfo;

@Repository("commentDao")

public class CommentDao extends BaseDao{
	
	
	public boolean addComments(String content, int motId, int usrId){
		Comments comment = new Comments();
		
		String hql = "FROM Moments e WHERE e.motId = ?";
		String hql1 = "FROM UserInfo e WHERE e.usrId = ?";
		list = getSession().createQuery(hql).setInteger(0, motId).list();
		
		if (!list.isEmpty()) {
			Moments moment = (Moments) list.get(0);
			moment.setMotCommentNum(moment.getMotCommentNum()+1);
			getSession().update(moment);
			
			list = getSession().createQuery(hql1).setInteger(0, usrId).list();
			UserInfo user = (UserInfo) list.get(0);
			
			comment.setComtContent(content);
			Date date = new Date();
			comment.setComtTime(date);		
			getSession().save(comment);
			comment.setMoments(moment);
			comment.setUserInfo(user);
			getSession().update(comment);
			getSession().flush();
			return true;
		} else
			return false;
	}
	
	public List showAllComments(int motId){
		String hql = "select e.commentses FROM Moments e WHERE e.motId = ?";
		List list = getSession().createQuery(hql).setInteger(0, motId).list();
//		for(int i = 0; i < list.size(); i++){
//			comment = (Comments) list.get(i);
//		}
		if(!list.isEmpty()){
			return list;
		}
		return null;
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
	
//	public boolean modifyComments(int motId, int comtId, String content){
//		String hql = "select comtId, comtContent, comtTime, userInfo FROM Moments e WHERE e.motId = ?";
//		list = getSession().createQuery(hql).setInteger(0, motId).list();
//		if(!list.isEmpty()){
//			comment.setComtContent(content);
//			Date date = new Date();
//			comment.setComtTime(date);
//			getSession().save(comment);
//		return true;
//		} else
//			return false;
//	}
}
