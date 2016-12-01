package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MomentDao;
@Service
@Transactional
public class MomentService {
	@Autowired
	MomentDao momentdao = new MomentDao();
	public boolean saveContent(String content,int id){
		momentdao.saveContent(content,id);
		return true;
	}
	public List showAllMoment(){
		return momentdao.showAllMoment();
	}
	public List showOwnMoment(int id){
		
		return momentdao.showOwnMoment(id);
	}
}
