package controller;

import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.UserInfo;
import entities.UserPassword;

public class SigninController {
	
	    public UserService userService;
	    @RequestMapping(value = "signin", method = RequestMethod.POST)
	    public boolean signin(UserInfo user,UserPassword u){
	    	if(!userService.isExist(user.getUsrName())){
	    		return userService.signin(user,u);
	    	}else{
	    		return false;//提示名字重复
	    	}
	    }
	    
	    
}
