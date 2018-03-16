/**
 * 
 * @author Imran
 *
 */
package com.dbs.redtrack.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.redtrack.business.service.AdminViewService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.model.ApplicationStatusFormBean;
import com.dbs.redtrack.integration.model.ApplicationStatusFormBean1;
import com.dbs.redtrack.integration.model.NewApplicationFormBean;
import com.dbs.redtrack.request.dto.ApplicationStatusDTO;
import com.dbs.redtrack.request.dto.ApplicationsDTO;
import com.dbs.redtrack.request.dto.BusinessUnitsDTO;
import com.dbs.redtrack.request.dto.CountryMasterDTO;
import com.dbs.redtrack.util.property.AppConfig;

/**
 * @author IBM
 * 
 *         Controller to manage admin views
 * 
 */
@Controller
public class AdminViewController {

	private static final Logger logger = Logger
			.getLogger(AdminViewController.class);

	@Autowired
	AppConfig appconfig;

	@Autowired
	ServletContext context;

	@Autowired
	AdminViewService adminViewService; 
	
	@RequestMapping(value = BusinessConstants.ADMIN_PAGE_ONE, method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView viewAddUpdateApplications() throws RedTrackProcessingException {
		logger.info("start AdminViewController :: viewAddUpdateApplications :: start WEB >>>>>> ");
		setContext();
		return new ModelAndView("index");
	}
	@RequestMapping(value ="/addNewApplicaiton", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView addNewApplicaiton() throws RedTrackProcessingException {
		logger.info("start AdminViewController :: addNewApplicaiton :: start WEB >>>>>> ");
		setContext();
		return new ModelAndView("addNewApplication");
	}
	
	@RequestMapping(value ="/updateApplicaitonStatus", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView updateApplicaitonStatus() throws RedTrackProcessingException {
		logger.info("start AdminViewController :: updateApplicaitonStatus :: start WEB >>>>>> ");
		setContext();
		return new ModelAndView("updateApplicaitonStatus");
	}

	@SuppressWarnings("unused")
	public void setContext() {

		logger.info("start AdminViewController :: setContext :: start");

		JSONArray jsonarrobj = new JSONArray();
		// System.out.println("Init method has been called");
		// ServletContext contextobj=null;
		JSONArray udidsArrObj = null;
		try {
			// daoObj=new DSSDaoOperations();
			// jsonarrobj=daoObj.getallAppTypes();
			jsonarrobj = getJsonArray();
			// context.setAttribute("masterdata", jsonarrobj.get(0));
			// List of application types
			context.setAttribute("listofapptypes", jsonarrobj.get(0));
			// List of Countries
			context.setAttribute("listofcountries", jsonarrobj.get(1));
			// List of Business Units
			context.setAttribute("listofbizunits", jsonarrobj.get(2));
			// context.setAttribute("listofudids", jsonarrobj.get(4));

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("END AdminViewController :: setContext :: END");
	}

	@SuppressWarnings("unchecked")
	public JSONArray getJsonArray() {

		logger.info("start AdminViewController :: getJsonArray :: start");

		JSONArray jsonarrobj = new JSONArray();

		// TODO:List Of Countries need to fill with DAO
		Map<String, String> listofcountries = new HashMap<>();
		List<CountryMasterDTO> countryList = adminViewService.getCountryList();
		
		 for(CountryMasterDTO str:countryList){
				listofcountries.put(str.getCountryCode(),str.getCountryDescription());
		  }
	
		
		// TODO:List Of business units need fill with DAO
		Map<String, String> listofbizunits = new HashMap<>();
		
		List<BusinessUnitsDTO> businessUnitList = adminViewService.getBusinessUnitList();
		
		  for(BusinessUnitsDTO str:businessUnitList){
			  listofbizunits.put(str.getUnitId(),str.getDescription());
		  }
	

		// TODO:List Of application types need to fill with DAO
		Map<String, String> listofapptypes = new HashMap<>();

		List<ApplicationsDTO> applicationList = adminViewService.getApplicationList();
			
			  for(ApplicationsDTO str:applicationList){
				  listofapptypes.put(str.getAppId(), str.getApplicationName());
			  }
		
		
		jsonarrobj.add(listofapptypes);
		jsonarrobj.add(listofcountries);
		jsonarrobj.add(listofbizunits);

		logger.info("start AdminViewController :: getJsonArray :: start");
		return jsonarrobj;

	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/submitapplicationFormBean",method = RequestMethod.POST)
	public String submitapplicationFormBean(Model model, ApplicationStatusFormBean applicationStatusFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response)throws RedTrackProcessingException {
		logger.info("start AdminViewController :: submitapplicationFormBean :: start WEB >>>>>> ");
		
		  String appid=applicationStatusFormBean.getAppid();
		  
	
		  
	
		 List<String> countriesList =  applicationStatusFormBean.getCountries();
		
	
	  List<String> businessUnitsList =  applicationStatusFormBean.getBusinessUnits();
	  String form="";
	  String StatusRemove=adminViewService.findandRemoveApplicationStatusById(appid);
			  for(String country:countriesList){
				  
				  for(String businessUnit:businessUnitsList){
					  
					  form=adminViewService.submitapplicationFormBean(appid,country,businessUnit);
					  
				  }
			  }
			  applicationStatusFormBean.setAppid(appid);
			  applicationStatusFormBean.setBusinessUnits(businessUnitsList);
			  applicationStatusFormBean.setCountries(countriesList);
			  applicationStatusFormBean.setNewappdtextid(form);
			  
		model.addAttribute("applicationStatusFormBean", applicationStatusFormBean);
		logger.info("start AdminViewController :: submitapplicationFormBean :: End >>>>>> ");
		return "successMember";
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/saveNewapplicationForm",method = RequestMethod.POST)
	public String saveNewapplicationForm(Model model, NewApplicationFormBean applicationFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response)throws RedTrackProcessingException {
		logger.info("start AdminViewController :: saveNewapplicationForm :: start WEB >>>>>> ");
		String applicationName=applicationFormBean.getApplicationName();;
		  String appId=applicationFormBean.getAppId();
		  String appCode=applicationFormBean.getAppCode();
		  String appActive=applicationFormBean.getAppActive();
		  String countryCode=applicationFormBean.getCountryCode();
	
				String	 form=adminViewService.addNewapplicationForm(appId,applicationName,appCode,appActive,countryCode);
			  
		model.addAttribute("applicationFormBean", applicationFormBean);
		logger.info("start AdminViewController :: saveNewapplicationForm :: END >>>>>> ");
		return "successMember";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/onchangeAppId",method = RequestMethod.POST)
	public ModelAndView onchangeAppId(Model model, ApplicationStatusFormBean applicationStatusFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response)throws RedTrackProcessingException {
		logger.info("start AdminViewController :: onchangeAppId :: start WEB >>>>>> ");
		
		  String appid=applicationStatusFormBean.getAppid();
		  
		  String status="";
	  Set<String>  listofselectedcountries = new HashSet<>();
	  Set<String> listofselectedbizunits = new HashSet<>();
		List<ApplicationStatusDTO> applicationStatusList = adminViewService.getApplicationStatusListById(appid);
			
			  for(ApplicationStatusDTO str:applicationStatusList){
				
				  listofselectedcountries.add(str.getCountryCode());
				  listofselectedbizunits.add(str.getUnitId());
				 
			  }
	
	  context.setAttribute("selectedcountries", listofselectedcountries);
	  context.setAttribute("selectedBusinessunits", listofselectedbizunits);
	  context.setAttribute("appid", appid);
	  context.setAttribute("appid", appid);
	  logger.info("start AdminViewController :: onchangeAppId :: End >>>>>> ");
	  return new ModelAndView("index");
	  


	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/onchangeAppIdforStatus",method = RequestMethod.POST)
	public ModelAndView onchangeAppIdforStatus(Model model, ApplicationStatusFormBean applicationStatusFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response)throws RedTrackProcessingException {
		logger.info("start AdminViewController :: onchangeAppIdforStatus :: start WEB >>>>>> ");
		
		  String appid=applicationStatusFormBean.getAppid();
		  
		  String status="";
	  Set<String>  listofselectedcountries = new HashSet<>();
	  Set<String> listofselectedbizunits = new HashSet<>();
	  Set<String> listofselectedstatus = new HashSet<>();
		List<ApplicationStatusDTO> applicationStatusList = adminViewService.getApplicationStatusListById(appid);
			
			  for(ApplicationStatusDTO str:applicationStatusList){
				 
				  listofselectedcountries.add(str.getCountryCode());
				  listofselectedbizunits.add(str.getUnitId());
				  listofselectedstatus.add(str.getStatus());
			  }
	  context.setAttribute("appid", appid);
	  context.setAttribute("applicationStatusList", applicationStatusList);
	  
	  ApplicationStatusFormBean1 applicationStatusFormBean1 = new ApplicationStatusFormBean1();
	  applicationStatusFormBean1.setApplicationStatusList(applicationStatusList);
		logger.info("start AdminViewController :: onchangeAppIdforStatus :: End >>>>>> ");
	  return new ModelAndView("updateApplicaitonStatus", "applicationStatusFormBean1", applicationStatusFormBean1);
	  


	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/saveApplicaitonStatusForm",method = RequestMethod.POST)
	public String saveApplicaitonStatusForm(@ModelAttribute("applicationStatusFormBean1") ApplicationStatusFormBean1 applicationStatusFormBean1, HttpServletRequest request)throws RedTrackProcessingException {
		logger.info("start AdminViewController :: saveApplicaitonStatusForm :: start WEB >>>>>> ");
		
		List<ApplicationStatusDTO> applicationStatusDTOList = applicationStatusFormBean1.getApplicationStatusList();
		String appId = request.getParameter("appidhidden");
		System.out.println(appId   + "    <<<<<<<<<<<<<<< applicationStatusDTO size ******************* " + applicationStatusDTOList.size());
		if(null != applicationStatusDTOList && applicationStatusDTOList.size() > 0) {
			
			for (ApplicationStatusDTO applicationStatusDTO : applicationStatusDTOList) {
				System.out.println("applicationStatusDTO.getCountryCode() code ******************* "+applicationStatusDTO.getCountryCode());
				String form=adminViewService.saveApplicaitonStatusForm(appId,applicationStatusDTO.getCountryCode(),applicationStatusDTO.getUnitId(),applicationStatusDTO.getStatus());
				
			}
		}
		logger.info("start AdminViewController :: saveApplicaitonStatusForm :: End>>>>>> ");
		return "successMember";
	}
}
