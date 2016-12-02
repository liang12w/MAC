package controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.LikeService;

/**
 * @author Louis Liu
 * @date Create Date：Nov 30, 2016 8:24:12 PM
 **/
@Controller
@RequestMapping(value = "/views")
public class Likes {
	@Autowired
	LikeService likeService;
	
	@RequestMapping(value = "addLikeAction", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addLike(int motId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!likeService.addLike(motId)){
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot add like.");
		}
		map.put("errorCode", 1);
		return map;
	}
	
	@RequestMapping(value = "removeLikeAction", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> removeLike(int motId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!likeService.removeLike(motId)){
			map.put("errorCode", 1);
			map.put("errorMsg", "Error! Cannot add like.");
		}
		map.put("errorCode", 1);
		return map;
	}
}
