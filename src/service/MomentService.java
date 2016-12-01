package service;

import java.util.List;

import dao.MomentDao;

public class MomentService {

	MomentDao momentdao = new MomentDao();
	public boolean saveContent(String content,int id){
		momentdao.saveContent(content,id);
		return true;
	}
	public List showAllMoment(){
		return momentdao.showAllMoment();
	}
}
