package controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.Comments;
import service.CommentService;

@Controller
@RequestMapping(value = "")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "addCommentAction",  method = RequestMethod.POST)
	public HashMap<String, Object> addComment(int motId, String content){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!commentService.addComment(content, motId)){
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot add comment.");
		}
		map.put("errorCode", 0);
		return map;
	}

	public HashMap<String, Object> deleteComment(int motId, int comtId){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!commentService.deleteComment(motId, comtId)){
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot delete comment.");
		}
		map.put("errorCode", 0);
		return map;
	}
	
	public HashMap<String, Object> showComment(int motId){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!commentService.showAllComments(motId).isEmpty()){
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot show comment.");
		}
		map.put("errorCode", 0);
		return map;
	}
	
	public HashMap<String, Object> modifyComment(int motId, int comtId, String content){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!commentService.modifyComment(motId, comtId, content)){
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot modify comment.");
		}
		map.put("errorCode", 0);
		return map;
	}
}
