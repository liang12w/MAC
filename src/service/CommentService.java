package service;

import org.springframework.stereotype.Service;

import dao.CommentDao;

@Service
public class CommentService {
	CommentDao commentDao = new CommentDao();
	public boolean addComment(String content, int motId){
		commentDao.addComment(content, modId);
		return true;
	}
	public List showAllComments(int motId){
		return commentDao.showAllComments();
	}
	public boolean deleteComment(int motId, int comtId){
		return deleteComment(modId, comtId);
	}
	public boolean modifyComment(int motId, int comtId, String content){
		return commentDao.midfyComment(motId, comtId, content);
	}
}
