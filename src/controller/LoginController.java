package controller;

import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.UserInfo;
import entities.UserPassword;


@Controller
@RequestMapping(value = "")
public class LoginController {
	
    @Autowired
    public UserService userService;

    @RequestMapping(value = "index", method = RequestMethod.POST)
    
    public String loginid(ModelMap map, String sid){
    	if(this.checkParams(new String[]{sid})){
    		if(userService.checkSid(sid)){
    			map.put("Security_id",sid);
    			return "cms/index";
    		}
    		 return "cms/error";
    	}
    	return "cms/error";
    }
    public String login(ModelMap map, String username, String password) {
        //验证传递过来的参数是否正确，否则返回到登陆页面。
        System.out.println(username);
        if (this.checkParams(new String[]{username, password})) {
            //指定要返回的页面为succ.jsp
            //ModelAndView mav = new ModelAndView("redirect:succ");
            //将参数返回给页面
            if (userService.check(username,password)) {
                map.put("usrName", username);
                map.put("usrPwd", password);
                return "cms/index";
            }
        }
        return "cms/error";
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

