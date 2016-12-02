package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import service.UserService;
import utils.HttpUtils;

@Controller
@RequestMapping(value = "")
public class MomentController {

	@Autowired
	MomentService momentservice = new MomentService();
	@Autowired
	UserService userservice = new UserService();

	@ResponseBody
	@RequestMapping(value = "views/submitMomentAction", method = RequestMethod.POST)
	public Map<String, Object> sendMoment(HttpServletRequest request, String motContent, String url) {
		int usrId = Integer.parseInt(request.getAttribute("usrId").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		if(momentservice.saveContent(motContent, usrId, url)){
			map.put("errorCode", 0);
			return map;
		} else {
			map.put("errorCode", 1);
			map.put("errorMsg", "put fail");
			return map;
		}
	}

	@ResponseBody
	@RequestMapping(value = "views/getMomentsAction", method = RequestMethod.POST)
	public Map<String, Object> showAllMoment(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int usrId = Integer.parseInt(request.getAttribute("usrId").toString());
		List list = momentservice.showAllMoment(usrId);
		userservice.refreshTime(usrId);
		
		if (list.size() != 0) {
			map.put("errorCode", 0);
			map.put("list", list);
			return map;
		} else {
			map.put("errorCode", 1);
			map.put("errorMsg", "no message");
			return map;
		}
	}

	@ResponseBody
	@RequestMapping(value = "views/getOwnMomentsAction", method = RequestMethod.POST)
	public Map<String, Object> showOwnMoment(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int usrId = Integer.parseInt(request.getAttribute("usrId").toString());
		List list = momentservice.showOwnMoment(usrId);
		userservice.refreshTime(usrId);
		if (list.size() != 0) {
			map.put("errorCode", 0);
			map.put("list", list);
			return map;
		} else {
			map.put("errorCode", 1);
			map.put("errorMsg", "no message");
			return map;
		}
		
	}

	@RequestMapping(value = "WatsonService", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> WatsonService(String content) {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey("cf12a4426504285e2a30fcebd1933f4c133141a7");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AlchemyLanguage.TEXT, content);
		String keyword = null;
		Entities entities = service.getEntities(map).execute();
		// System.out.println("Entities: " + entities);
		String tem = entities.getEntities().toString();
		if (tem == null || tem == "[]") {
			keyword = "sky";
		} else {
			JSONArray array = JSON.parseArray(tem);
			JSONObject entity = array.getJSONObject(0);
			keyword = entity.getString("text");
		}
		String url = genGif(keyword);
		// String type = entity.getString("type");
		map.remove(AlchemyLanguage.TEXT);
		map.put("errorCode", 0);
		map.put("url", url);
		return map;
	}

	public String genGif(String keyword) {
		String content = "http://api.giphy.com/v1/gifs/search?q=" + keyword + "&api_key=dc6zaTOxFJmzC";
		String text = HttpUtils.getHttpResult(content);
		JSONObject json = JSON.parseObject(text);
		JSONArray data = JSON.parseArray(json.getString("data"));
		Random random = new Random();
		int index = random.nextInt(25);
		String url = JSON.parseObject(data.getString(index)).getJSONObject("images").getJSONObject("downsized")
				.getString("url");
		return url;
	}

}