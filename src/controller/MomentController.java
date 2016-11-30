package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Moments;
import service.MomentService;

//@Controller
//@RequestMapping(value = "")
public class MomentController {

//	@Autowired
	public void sendMoment(String motContent,int id){
		MomentService momentservice = new MomentService();
	    momentservice.saveContent(motContent,id);
	}

}
