package dao;

import org.springframework.stereotype.Repository;

import entities.Moments;

/**
 * @author Louis Liu
 * @date Create Date：Nov 30, 2016 8:05:11 PM
 **/

@Repository("likeDao")
public class LikeDao extends BaseDao {
	public boolean addLike(int motId) {
		String hql = "FROM Moments e WHERE e.motId = ?";
		list = getSession().createQuery(hql).setInteger(0, motId).list();
		if (!list.isEmpty()) {
			Moments moment = (Moments) list.get(0);
			moment.setMotLikeNum(moment.getMotLikeNum() + 1);
			getSession().save(moment);
			return true;
		} else
			return false;
	}
	public boolean removeLike(int motId) {
		String hql = "FROM Moments e WHERE e.motId = ?";
		list = getSession().createQuery(hql).setInteger(0, motId).list();
		if (!list.isEmpty()) {
			Moments moment = (Moments) list.get(0);
			moment.setMotLikeNum(moment.getMotLikeNum() - 1);
			getSession().save(moment);
			return true;
		} else
			return false;
	}
}
