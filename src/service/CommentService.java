package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CommentDao;

@Service
@Transactional
public class CommentService {
	@Autowired
	CommentDao commentDao = new CommentDao();

	public boolean addComment(String content, int motId) {
		commentDao.addComments(content, motId);
		return true;
	}

	public List showAllComments(int motId) {
		return commentDao.showAllComments(motId);
	}

	public boolean deleteComment(int motId, int comtId) {
		return deleteComment(motId, comtId);
	}

	public boolean modifyComment(int motId, int comtId, String content) {
		return commentDao.modifyComments(motId, comtId, content);
	}
}
