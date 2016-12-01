package service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LikeDao;

/** 
* @author  Louis Liu 
* @date Create Date：Nov 30, 2016 8:20:28 PM 
**/
@Service
@Transactional
public class LikeService {
	@Autowired
    public LikeDao likeDao;
	
	public boolean addLike(int motId){
		return likeDao.addLike(motId);
	}
	
	public boolean removeLike(int motId){
		return likeDao.removeLike(motId);
	}
}
