package com.dbs.redtrack.controller.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.redtrack.jpa.entity.Employee;


@Controller
public class HelloPageController { 
	
	private static final Logger logger = Logger.getLogger(HelloPageController.class);
	 
 
	
	
	@RequestMapping("/hello")
	public ModelAndView mymethod(){ 
		logger.info("inside hello");  
		logger.info("before registerEmployee");
 
		Employee employee2 = new Employee();
		employee2.setEmployeeHireDate(new Date());
		employee2.setEmployeeId(5);
		employee2.setEmployeeName("RAYMOND CABANSAG");
		employee2.setEmployeeSalary(1000);
		  
//		model.addAttribute("job",job)
		
		return new ModelAndView("hellopage","message","Saved Record Succes!!");
		
	}
}
