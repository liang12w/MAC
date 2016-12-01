package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	@RequestMapping(value = "WatsonService", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> WatsonService(String content) {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("cf12a4426504285e2a30fcebd1933f4c133141a7");

		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(AlchemyLanguage.TEXT, content);
	    
	    Entities entities = service.getEntities(map).execute();
//	    System.out.println("Entities: " + entities);
	    JSONArray array = JSON.parseArray(entities.getEntities().toString());
	    JSONObject entity = array.getJSONObject(0);
	    String keyword = entity.getString("text");
	    if(keyword == null || keyword == ""){
	    	keyword = "sky";
	    }
	    String url = genGif(keyword);
//	    String type = entity.getString("type");
	    map.remove(AlchemyLanguage.TEXT);
		map.put("errorCode", 0);
		map.put("url", url);
		return map;
	}
	public String genGif(String keyword){
		String content = "http://api.giphy.com/v1/gifs/search?q="+keyword+"&api_key=dc6zaTOxFJmzC";
		String text = HttpUtils.getHttpResult(content);
		JSONObject json = JSON.parseObject(text);
		JSONArray data  = JSON.parseArray(json.getString("data"));
		Random random = new Random();  
		int index = random.nextInt(10);
		String url = JSON.parseObject(data.getString(index)).getJSONObject("images").getJSONObject("downsized").getString("url");
		return url;
	}
	
}