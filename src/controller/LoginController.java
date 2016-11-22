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
    
    public String loginid(ModelMap map, UserInfo user){
    	if(this.checkParams(new String[]{user.getSecurityId()})){
    		if(userService.checkSid(user)){
    			map.put("Security_id",user.getSecurityId());
    			return "cms/index";
    		}
    		 return "cms/error";
    	}
    	return "cms/error";
    }
    public String login(ModelMap map, UserInfo user, UserPassword u) {
        //验证传递过来的参数是否正确，否则返回到登陆页面。
        System.out.println(user);
        if (this.checkParams(new String[]{user.getUsrName(), u.getUsrPwd()})) {
            //指定要返回的页面为succ.jsp
            //ModelAndView mav = new ModelAndView("redirect:succ");
            //将参数返回给页面
            if (userService.check(user,u)) {
                map.put("usrName", user.getUsrName());
                map.put("usrPwd", u.getUsrPwd());
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

