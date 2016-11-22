package controller;

import utils.HttpUtils;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "")
public class Tests {
	static int counter = 0;
	static HashMap<String, Object>map = new HashMap<String, Object>();
	
	@RequestMapping(value = "loginAction", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> testLogin(String username, String password) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sid", password);
		map.put("errorCode", 0);
		return map;
	}

	@RequestMapping(value = "getGifAction", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doHttpPost() {
		counter++;
		String data = HttpUtils.getHttpResult("http://api.giphy.com/v1/gifs/search?q=funny+cat&api_key=dc6zaTOxFJmzC");
		JSONObject json = null;
		if(!data.equalsIgnoreCase("http error")){
			json = JSON.parseObject(data);
		}
		System.out.println(counter + " Data is "+json.toString());
		map.put("errorCode", 0);
		map.put("gifJson", json.toString());
		return map;
	}

}
