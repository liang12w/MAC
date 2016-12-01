package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;

import entities.Moments;
import service.MomentService;
import utils.HttpUtils;

@Controller
@RequestMapping(value = "")
public class MomentController {

	@Autowired
	MomentService momentservice = new MomentService();

	@RequestMapping(value = "views/submitMomentAction",method = RequestMethod.POST)
	public void sendMoment(String motContent, int id, String url) {
		momentservice.saveContent(motContent, id, url);
	}

	@RequestMapping(value = "views/getMomentsAction", method = RequestMethod.POST)
	public List showAllMoment(int id) {
		return momentservice.showAllMoment(id);
	}

	@RequestMapping(value = "views/getOwnMomentsAction", method = RequestMethod.POST)
	public List showOwnMoment(int id) {
		return momentservice.showOwnMoment(id);
	}
	@RequestMapping(value = "testWatson", method = RequestMethod.POST)
	@ResponseBody
	public String watson(String content) {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("cf12a4426504285e2a30fcebd1933f4c133141a7");

		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(AlchemyLanguage.TEXT, "The technology definitely has its limits. It can't produce videos that go further than 1.5 seconds into the future, and the results aren't mind-blowingly realistic: it isn't aware that objects are still there when they move, and tends to exaggerate their sizes. However, it's good enough to predict relatively complicated scenes like waves on the beach, or people walking on grass. If CSAIL can extend predictions and make them more realistic, though, it could have a far-reaching impact. Self-driving cars could predict where vehicles and pedestrians are going, while security cameras could spot mismatches in footage based on what they expect to see. It could also be used for relatively everyday tasks like adding animation to still images or compressing videos (since you wouldn't need every frame). And regardless of circumstances, predicting the future can help AI understand what's going on right now -- this could help with just about any instance where computer vision is important.");
	    
	    Entities entities = service.getEntities(map).execute();
	    System.out.println("Entities: " + entities);
	    JSONArray array = JSON.parseArray(entities.getEntities().toString());
	    JSONObject entity = array.getJSONObject(0);
	    String keyword = entity.getString("text");
	    String url = genGif(keyword);
	    String type = entity.getString("type");
	    System.out.println("text is "+keyword+" type is "+ type);
	    return url;
//		map.put("errorCode", 0);
//		return map;
	}
	public String genGif(String keyword){
		JSONArray jsonArray = (JSONArray) JSON.parse(HttpUtils.getHttpResult("http://api.giphy.com/v1/gifs/search?q="+keyword+"&api_key=dc6zaTOxFJmzC"));
		JSONObject entity = jsonArray.getJSONObject(0);
	    String url = entity.getString("text");
		return url;
	}
	
}