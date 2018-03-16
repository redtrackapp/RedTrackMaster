package com.dbs.redtrack.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 

@Controller
public class CustomerController {

    @Autowired
    CustomerValidator validator;
    @Autowired
    ServletContext context; 
    
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("hellopage");
        modelAndView.getModel().put("message", "hello Raymond!");
        return modelAndView;
 
    }
//    
//    public void push() {
// 
//		try
//		{
//			ArrayList tokenlist=null; //daoObj.getTokenlistforiOS();
//			if(tokenlist!=null && tokenlist.size()>0)
//			{
//				String payload=null;
//				try
//				{
//					String certsrootpath = context.getRealPath("certspath");
//					certsrootpath=certsrootpath+File.separator+"RedTrack_APNS_Prod_Certificates.p12";
//					System.out.println("The new complete path is ==> "+certsrootpath);
//					NoteClass aa=new NoteClass();
//					aa.sendnotes(certsrootpath,tokenlist,notificationMsg);
//				}
//				catch(Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//    	
//    }
    
    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("hellopage");
        modelAndView.getModel().put("message", "hello Raymond!");
        return modelAndView;
 
    }
    
    
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView showForm() {
    	
    	
        return new ModelAndView("customerHome", "customer", new Customer("1", "myName", "90876547", "test@sg.ibm.com"));
    }
    
    @RequestMapping(value = "/customer1", method = RequestMethod.GET)
    public String handleRequest(Model model){
    	   //arbitrary handling code
    	Map<String, String> listofapptypes = new HashMap<String, String>();
    	Map<String, String> listofcountries = new HashMap<String, String>();
    	Map<String, String> listofbizunits = new HashMap<String, String>();
    	listofapptypes.put("appid1", "App Desc");
    	listofapptypes.put("appid2", "App Desc2");
    	listofapptypes.put("appid3", "App Desc3");

    	listofcountries.put("countryCd1", "Country Desc");
    	listofcountries.put("countryCd2", "Country Desc2");
    	listofcountries.put("countryCd3", "Country Desc3");

    	listofbizunits.put("bizid1", "Biz Desc");
    	listofbizunits.put("bizid2", "Biz Desc2");
    	listofbizunits.put("bizid3", "Biz Desc3");

    	
    	   model.addAttribute("listofapptypes", listofapptypes);
    	   model.addAttribute("listofcountries", listofcountries);
    	   model.addAttribute("listofbizunits", listofbizunits);
    	   
    	   //etc
    	   return "index1";
    	 }
    
    
    
    @RequestMapping(value = "/appstatus", method = RequestMethod.GET)
    public String appStatus(Model model){
 
    	AppstatusDTO appstatus = new AppstatusDTO("111", "App1", "1", "App1 Desc");
    	List<AppstatusDTO> appstatusarrayobj = new ArrayList<AppstatusDTO>();
    	appstatusarrayobj.add(appstatus);
    	appstatusarrayobj.add(new AppstatusDTO("222", "App2", "2", "App2 Desc") );
    	appstatusarrayobj.add(new AppstatusDTO("3333", "App3", "3", "App3 Desc") );
    	
    	model.addAttribute("appstatusarrayobj", appstatusarrayobj);
    	
    	return "appstatus2";
    }

    
    
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST) 
    public String submit(@Valid @ModelAttribute("customer") final Customer customer, final BindingResult result, final ModelMap model) {
        validator.validate(customer, result);
        if (result.hasErrors()) {
            return "customerHome";
        }
        model.addAttribute("customerId", customer.getCustomerId());
        model.addAttribute("customerName", customer.getCustomerName());
        model.addAttribute("customerContact", customer.getCustomerContact());
        model.addAttribute("customerEmail", customer.getCustomerEmail());
        return "customerView";
    }

}
