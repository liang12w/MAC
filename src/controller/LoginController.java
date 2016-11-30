package controller;

import service.UserService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entities.UserInfo;
import entities.UserPassword;


@Controller
@RequestMapping(value = "")
public class LoginController {
	
	@Autowired
    public UserService userService;

	@ResponseBody
    @RequestMapping(value = "index", method = RequestMethod.POST)
    
    public HashMap loginid( String sid){
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	if(this.checkParams(new String[]{sid})){
    		if(userService.checkSid(sid)){
    			
    			map.put("errorCode", 0);
    			return map;
    		}
    		map.put("errorCode", 1);
    		map.put("errorMsg", "error security id" );
    		return map;
    	}
    	map.put("errorCode", 1);
    	map.put("errorMsg", "error security id");
    	return map;
    }
    public HashMap login(String username, String password) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
        //验证传递过来的参数是否正确，否则返回到登陆页面。
        System.out.println(username);
        if (this.checkParams(new String[]{username, password})) {
            //指定要返回的页面为succ.jsp
            //ModelAndView mav = new ModelAndView("redirect:succ");
            //将参数返回给页面
            if (userService.check(username,password)) {
                
                map.put("errorCode", 0);
                return map;
            }
        }
       map.put("errorCode", 1);
       map.put("errorMsg", "wrong username or password");
       return map;
    }

    /**
     * *
     * 验证参数
     *
     * @param params
     * @return
     */
    private boolean checkParams(String[] params) {
        for (String param : params) {
            if (param == "" || param == null || param.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

