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
public class SigninController {
	@Autowired
	public UserService userService;

	@RequestMapping(value = "signupAction", method = RequestMethod.POST)
	public boolean signin(String userName, String nickname, String email,String password) {
		UserInfo user = new UserInfo();
		UserPassword u = new UserPassword();
		user.setUsrName(userName);
		user.setUsrNickname(nickname);
		user.setUsrEmail(email);
		u.setUsrId(user.getUsrId());
		u.setUsrPwd(password);
		if (!userService.isExist(user.getUsrName())) {
			return userService.signUp(user, u);
		} else {
			return false;// 提示名字重复
		}
	}

}
