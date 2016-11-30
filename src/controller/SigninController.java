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
public class SigninController {
	@Autowired
	public UserService userService;

	@ResponseBody
	@RequestMapping(value = "signupAction", method = RequestMethod.POST)
	public HashMap signin(String username, String usrNickname, String email, String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!userService.isExist(username)) {
			UserInfo user = new UserInfo();
			UserPassword u = new UserPassword();
			user.setUsrName(username);
			user.setUsrNickname(usrNickname);
			user.setUsrEmail(email);
			if (userService.signUp(user)) {
				u.setUsrId(user.getUsrId());
				u.setUsrPwd(password);
				userService.savePassword(user, u);
			}
			map.put("errorCode", 0);
			return map;
		} else {
			map.put("errorCode", 1);
			map.put("errorMsg", "user name exists");
			return map;// 提示名字重复
		}
	}

}
