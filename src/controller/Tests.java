package controller;
import utils.HttpUtils;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "")
public class Tests {
	
    @RequestMapping(value = "loginAction", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> testLogin(String username, String password){
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("sid", password);
    	map.put("errorCode", 0);
    	return map; 
    }
    
    @RequestMapping(value = "/views/getGifAction", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> doHttpPost() {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	try {
			System.out.println(HttpUtils.getHttpResult("http://api.giphy.com/v1/gifs/search?q=funny+cat&api_key=dc6zaTOxFJmzC"));
			map.put("errorCode", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("errorCode", 1);
		}
    	return map;
    }
}
