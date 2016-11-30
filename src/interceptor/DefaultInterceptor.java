package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import service.UserService;

/**
 * @author Louis Liu
 * @date Create Date：Nov 28, 2016 6:38:31 PM
 **/
public class DefaultInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getParameter("sid"));
		UserService userService = new UserService();
		if(userService.checkSid(request.getParameter("sid"))==true)
			return true;
		else{
			response.getWriter().print("{\"errorCode\":-1,\"errorMsg\":\"Invalid user login inforamtion\"}");
			return false;			
		}
		// return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
