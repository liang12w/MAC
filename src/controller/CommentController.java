package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.CommentService;

@Controller
@RequestMapping(value = "/views")
public class CommentController {

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "submitCommentsAction", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addComment(HttpServletRequest request, int motId, @RequestParam(value = "comment") String content) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int usrId = Integer.parseInt(request.getAttribute("usrId").toString());
		if (!commentService.addComment(content, motId, usrId)) {
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot add comment.");
			return map;
		}
		map.put("errorCode", 0);
		return map;
	}

	@RequestMapping(value = "getCommentsAction", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> showComment(int motId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List list = commentService.showAllComments(motId);
		if (list==null) {
			map.put("errorCode", 1);
			map.put("errorMsg", "Do not have comment.");
			return map;
		}
		map.put("errorCode", 0);
		map.put("list", list);
		return map;
	}
	
	public HashMap<String, Object> deleteComment(int motId, int comtId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!commentService.deleteComment(motId, comtId)) {
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot delete comment.");
		}
		map.put("errorCode", 0);
		return map;
	}

//	public HashMap<String, Object> modifyComment(int motId, int comtId, String content) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		if (!commentService.modifyComment(motId, comtId, content)) {
//			map.put("errorCode", 1);
//			map.put("errorMsg", "Error! Cannot modify comment.");
//		}
//		map.put("errorCode", 0);
//		return map;
//	}
}
