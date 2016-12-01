package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Moments;
import service.MomentService;

//@Controller
//@RequestMapping(value = "")
public class MomentController {

//	@Autowired
	MomentService momentservice = new MomentService();
	public void sendMoment(String motContent,int id){
	    momentservice.saveContent(motContent,id);
	}
	public List showAllMoments(){
		return momentservice.showAllMoment();
	}

}
