package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Moments;

//@Controller
//@RequestMapping(value = "")
public class MomentController {

//	@Autowired
	public void sendMoment(String motContent,int id){
		Moments moment = new Moments();
		moment.setMotId(id);
		moment.setMotContent(motContent);
	}

}
