package controller;

import utils.HttpUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;

@Controller
@RequestMapping(value = "")
public class Tests {

	// @RequestMapping(value = "loginAction", method = RequestMethod.POST)
	// @ResponseBody
	// public HashMap<String, Object> testLogin(String username, String
	// password){
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// map.put("sid", password);
	// map.put("errorCode", 0);
	// return map;
	// }

	@RequestMapping(value = "getGifAction", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doHttpPost() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println(
					HttpUtils.getHttpResult("http://api.giphy.com/v1/gifs/search?q=funny+cat&api_key=dc6zaTOxFJmzC"));
			map.put("errorCode", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("errorCode", 1);
		}
		return map;
	}

	@RequestMapping(value = "testWatson", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> watson() {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("cf12a4426504285e2a30fcebd1933f4c133141a7");

		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(AlchemyLanguage.TEXT, "The technology definitely has its limits. It can't produce videos that go further than 1.5 seconds into the future, and the results aren't mind-blowingly realistic: it isn't aware that objects are still there when they move, and tends to exaggerate their sizes. However, it's good enough to predict relatively complicated scenes like waves on the beach, or people walking on grass. If CSAIL can extend predictions and make them more realistic, though, it could have a far-reaching impact. Self-driving cars could predict where vehicles and pedestrians are going, while security cameras could spot mismatches in footage based on what they expect to see. It could also be used for relatively everyday tasks like adding animation to still images or compressing videos (since you wouldn't need every frame). And regardless of circumstances, predicting the future can help AI understand what's going on right now -- this could help with just about any instance where computer vision is important.");
	    
	    Entities entities = service.getEntities(map).execute();
	    System.out.println("Entities: " + entities);
	    JSONArray array = JSON.parseArray(entities.getEntities().toString());
	    JSONObject entity = array.getJSONObject(0);
	    String keyword = entity.getString("text");
	    String type = entity.getString("type");
	    
	    System.out.println("text is "+keyword+" type is "+ type);
	    
		map.put("errorCode", 0);
		return map;
	}
}
