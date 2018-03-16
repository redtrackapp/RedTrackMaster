package com.dbs.redtrack.controller;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.redtrack.business.service.CallHistoryService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.CallHistoryRequestDTO;
import com.dbs.redtrack.response.dto.CallHistoryResponseDTO;
import com.dbs.redtrack.response.dto.GetCallHistoryResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;

@Controller
public class CallHistoryController {

	private static final Logger logger = Logger.getLogger(CallHistoryController.class);

	@Autowired
	CallHistoryService callHistoryService;


    
	@Autowired
	AppConfig appconfig;

	/**
	 * Created by: Raymond Method name: saveCallHistory() @throws
	 * 
	 */
	@RequestMapping(value = BusinessConstants.SAVE_CALL_HISTORY, method = RequestMethod.POST)
	public @ResponseBody CallHistoryResponseDTO saveCallHistory(@RequestParam("incidentid") String incidentid,
			@RequestParam("incidentname") String incidentname, @RequestParam("userotpid") String userotpid,
			@RequestParam("participantcode") String participantcode,
			@RequestParam("callhistorydate") String callhistorydate,
			@RequestParam("callhistorytime") String callhistorytime, @RequestParam("callhistname") String callhistname,
			@RequestParam("categoryname") String categoryname, @RequestParam("phonenumber") String phonenumber)
			throws RedTrackProcessingException {

		logger.info("start CallHistoryController :: saveCallHistory :: start");
		CallHistoryRequestDTO request = new CallHistoryRequestDTO(incidentid, incidentname, userotpid, participantcode,
				callhistorydate, callhistorytime, callhistname, categoryname, phonenumber);

		// if(!RequestValidator.validateGenerateOTPRequest(request)) {
		// throw new RedTrackProcessingException(BusinessConstants.ERROR_00001,
		// BusinessConstants.INVALID_REQUEST);
		// }
		//
		CallHistoryResponseDTO callHistoryResponseDTO = callHistoryService.saveCallHistory(request);

		logger.info("start CallHistoryController :: saveCallHistory :: end");
		return callHistoryResponseDTO;
	}

	@RequestMapping(value = BusinessConstants.GET_CALL_HISTORY, method = RequestMethod.POST)
	public @ResponseBody GetCallHistoryResponseDTO getCallHistory(@RequestParam("userotpid") String userotpid)
			throws RedTrackProcessingException {

			
		logger.info("start CallHistoryController :: getCallHistory :: start");

		// if(!RequestValidator.validateGenerateOTPRequest(request)) {
		// throw new RedTrackProcessingException(BusinessConstants.ERROR_00001,
		// BusinessConstants.INVALID_REQUEST);
		// }
		//
		GetCallHistoryResponseDTO getCallHistoryResponseDTO = callHistoryService.getCallHistory(userotpid);

		logger.info("start CallHistoryController :: getCallHistory :: end");
		return getCallHistoryResponseDTO;
	}
	
	

	@RequestMapping(value = BusinessConstants.GET_CALL_HISTORY+"test", method = RequestMethod.POST)
	public @ResponseBody GetCallHistoryResponseDTO getCallHistoryTest(@RequestParam("userotpid") String userotpid)
			throws RedTrackProcessingException {

		logger.info("start CallHistoryController :: getCallHistory :: start");

		// if(!RequestValidator.validateGenerateOTPRequest(request)) {
		// throw new RedTrackProcessingException(BusinessConstants.ERROR_00001,
		// BusinessConstants.INVALID_REQUEST);
		// }
		//
		GetCallHistoryResponseDTO getCallHistoryResponseDTO = callHistoryService.getCallHistoryTest(userotpid);

		logger.info("start CallHistoryController :: getCallHistory :: end");
		return getCallHistoryResponseDTO;
	}

	//
	// @SuppressWarnings("rawtypes")
	// @RequestMapping(value = "1", method = RequestMethod.POST)
	// public @ResponseBody ResponseEntity<ResponseDTO>
	// generateOTP1(@RequestBody RequestDTO<GenerateOTPRequest> request) throws
	// RedTrackProcessingException, TwilioRestException{
	// logger.info("start RegistrationController :: generateOTP :: start");
	// ResponseDTO<RegisterUserResponseDTO> responsedto = new
	// ResponseDTO<RegisterUserResponseDTO>();
	//
	// if(!RequestValidator.validateHeader(request)||
	// !RequestValidator.validateOTPRequest(request)) {
	// throw new RedTrackProcessingException(BusinessConstants.ERROR_00001,
	// BusinessConstants.INVALID_REQUEST);
	// }
	//
	// //RegisterUserResponseDTO registerUserResponseDTO =
	// userRegistrationService.generateOTP(request.getBody());
	// //responsedto.setBody(registerUserResponseDTO);
	// responsedto.setHeader(request.getHeader());
	//
	// logger.info("start RegistrationController :: generateOTP :: end");
	// return new ResponseEntity<ResponseDTO>(responsedto, HttpStatus.OK);
	// }

	// String jsonInString2 = "{ \"noOfCards\": \"3\", \"monthlyTotals\": [ {
	// \"date\": \"2016-11-30\", \"formattedDate\": \"November 2016\",
	// \"currency\": \"SGD\", \"amount\": \"500.00\", \"formattedAmount\":
	// \"500.00\" }, { \"date\": \"2016-12-31\", \"formattedDate\": \"Decemeber
	// 2016\", \"currency\": \"SGD\", \"amount\": \"1000.00\",
	// \"formattedAmount\": \"1,000.00\" }, { \"date\": \"2017-01-31\",
	// \"formattedDate\": \"January 2017\", \"currency\": \"SGD\", \"amount\":
	// \"1000.00\", \"formattedAmount\": \"1,000.00\" }, { \"date\":
	// \"2017-02-28\", \"formattedDate\": \"Febuary 2017\", \"currency\":
	// \"SGD\", \"amount\": \"1000.00\", \"formattedAmount\": \"1,000.00\" }, {
	// \"date\": \"2017-03-31\", \"formattedDate\": \"March 2017\",
	// \"currency\": \"SGD\", \"amount\": \"1000.00\", \"formattedAmount\":
	// \"1,000.00\" }, { \"date\": \"2017-04-22\", \"formattedDate\": \"April
	// 2017\", \"currency\": \"SGD\", \"amount\": \"1000.00\",
	// \"formattedAmount\": \"1,000.00\" } ], \"monthlyCategoryTotals\": [ {
	// \"date\": \"2016-11-30\", \"formattedDate\": \"November 2016\",
	// \"amount\": \"500.00\", \"formattedAmount\": \"500.00\",
	// \"categoryTotals\": [ { \"categoryCode\": \"Shopping\",
	// \"categoryLabel\": \"Shopping\", \"categoryImage\": \"e914\",
	// \"colorCode\": \"FFBB33\", \"currency\": \"SGD\", \"amount\": \"200.00\",
	// \"formattedAmount\": \"200.00\", \"percent\": \"0.4\",
	// \"formattedPercent\": \"40%\" }, { \"categoryCode\": \"FoodDining\",
	// \"categoryLabel\": \"Food & Dining\", \"categoryImage\": \"e909\",
	// \"colorCode\": \"FF5544\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.2\",
	// \"formattedPercent\": \"20%\" }, { \"categoryCode\": \"Entertainment\",
	// \"categoryLabel\": \"Entertainment\", \"categoryImage\": \"e904\",
	// \"colorCode\": \"EE88CC\", \"currency\": \"SGD\", \"amount\": \"50.00\",
	// \"formattedAmount\": \"50.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"BillsUtilities\",
	// \"categoryLabel\": \"Bills & Utilities\", \"categoryImage\": \"e900\",
	// \"colorCode\": \"BB66CC\", \"currency\": \"SGD\", \"amount\": \"50.00\",
	// \"formattedAmount\": \"50.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"Transportation\",
	// \"categoryLabel\": \"Transportation\", \"categoryImage\": \"e916\",
	// \"colorCode\": \"8855DD\", \"currency\": \"SGD\", \"amount\": \"50.00\",
	// \"formattedAmount\": \"50.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"HealthFitness\",
	// \"categoryLabel\": \"Health & Fitness\", \"categoryImage\": \"e90c\",
	// \"colorCode\": \"909090\", \"currency\": \"SGD\", \"amount\": \"50.00\",
	// \"formattedAmount\": \"50.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" } ] }, { \"date\": \"2016-12-31\",
	// \"formattedDate\": \"December 2016\", \"amount\": \"1000.00\",
	// \"formattedAmount\": \"1,000.00\", \"categoryTotals\": [ {
	// \"categoryCode\": \"Shopping\", \"categoryLabel\": \"Shopping\",
	// \"categoryImage\": \"e914\", \"colorCode\": \"FFBB33\", \"currency\":
	// \"SGD\", \"amount\": \"400.00\", \"formattedAmount\": \"400.00\",
	// \"percent\": \"0.4\", \"formattedPercent\": \"40%\" }, {
	// \"categoryCode\": \"FoodDining\", \"categoryLabel\": \"Food & Dining\",
	// \"categoryImage\": \"e909\", \"colorCode\": \"FF5544\", \"currency\":
	// \"SGD\", \"amount\": \"200.00\", \"formattedAmount\": \"200.00\",
	// \"percent\": \"0.2\", \"formattedPercent\": \"20%\" }, {
	// \"categoryCode\": \"Entertainment\", \"categoryLabel\":
	// \"Entertainment\", \"categoryImage\": \"e904\", \"colorCode\":
	// \"EE88CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"BillsUtilities\",
	// \"categoryLabel\": \"Bills & Utilities\", \"categoryImage\": \"e900\",
	// \"colorCode\": \"BB66CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"Transportation\",
	// \"categoryLabel\": \"Transportation\", \"categoryImage\": \"e916\",
	// \"colorCode\": \"8855DD\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"HealthFitness\",
	// \"categoryLabel\": \"Health & Fitness\", \"categoryImage\": \"e90c\",
	// \"colorCode\": \"909090\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" } ] }, { \"date\": \"2017-01-31\",
	// \"formattedDate\": \"January 2017\", \"amount\": \"1000.00\",
	// \"formattedAmount\": \"1,000.00\", \"categoryTotals\": [ {
	// \"categoryCode\": \"Shopping\", \"categoryLabel\": \"Shopping\",
	// \"categoryImage\": \"e914\", \"colorCode\": \"FFBB33\", \"currency\":
	// \"SGD\", \"amount\": \"400.00\", \"formattedAmount\": \"400.00\",
	// \"percent\": \"0.4\", \"formattedPercent\": \"40%\" }, {
	// \"categoryCode\": \"FoodDining\", \"categoryLabel\": \"Food & Dining\",
	// \"categoryImage\": \"e909\", \"colorCode\": \"FF5544\", \"currency\":
	// \"SGD\", \"amount\": \"200.00\", \"formattedAmount\": \"200.00\",
	// \"percent\": \"0.2\", \"formattedPercent\": \"20%\" }, {
	// \"categoryCode\": \"Entertainment\", \"categoryLabel\":
	// \"Entertainment\", \"categoryImage\": \"e904\", \"colorCode\":
	// \"EE88CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"BillsUtilities\",
	// \"categoryLabel\": \"Bills & Utilities\", \"categoryImage\": \"e900\",
	// \"colorCode\": \"BB66CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"Transportation\",
	// \"categoryLabel\": \"Transportation\", \"categoryImage\": \"e916\",
	// \"colorCode\": \"8855DD\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"HealthFitness\",
	// \"categoryLabel\": \"Health & Fitness\", \"categoryImage\": \"e90c\",
	// \"colorCode\": \"909090\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" } ] }, { \"date\": \"2017-02-28\",
	// \"formattedDate\": \"Febuary 2017\", \"amount\": \"1000.00\",
	// \"formattedAmount\": \"1,000.00\", \"categoryTotals\": [ {
	// \"categoryCode\": \"Shopping\", \"categoryLabel\": \"Shopping\",
	// \"categoryImage\": \"e914\", \"colorCode\": \"FFBB33\", \"currency\":
	// \"SGD\", \"amount\": \"400.00\", \"formattedAmount\": \"400.00\",
	// \"percent\": \"0.1\", \"formattedPercent\": \"10%\" }, {
	// \"categoryCode\": \"FoodDining\", \"categoryLabel\": \"Food & Dining\",
	// \"categoryImage\": \"e909\", \"colorCode\": \"FF5544\", \"currency\":
	// \"SGD\", \"amount\": \"200.00\", \"formattedAmount\": \"200.00\",
	// \"percent\": \"0.1\", \"formattedPercent\": \"10%\" }, {
	// \"categoryCode\": \"Entertainment\", \"categoryLabel\":
	// \"Entertainment\", \"categoryImage\": \"e904\", \"colorCode\":
	// \"EE88CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"BillsUtilities\",
	// \"categoryLabel\": \"Bills & Utilities\", \"categoryImage\": \"e900\",
	// \"colorCode\": \"BB66CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"Transportation\",
	// \"categoryLabel\": \"Transportation\", \"categoryImage\": \"e916\",
	// \"colorCode\": \"8855DD\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"HealthFitness\",
	// \"categoryLabel\": \"Health & Fitness\", \"categoryImage\": \"e90c\",
	// \"colorCode\": \"909090\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" } ] }, { \"date\": \"2017-03-31\",
	// \"formattedDate\": \"March 2017\", \"amount\": \"1000.00\",
	// \"formattedAmount\": \"1,000.00\", \"categoryTotals\": [ {
	// \"categoryCode\": \"Shopping\", \"categoryLabel\": \"Shopping\",
	// \"categoryImage\": \"e914\", \"colorCode\": \"FFBB33\", \"currency\":
	// \"SGD\", \"amount\": \"400.00\", \"formattedAmount\": \"400.00\",
	// \"percent\": \"0.4\", \"formattedPercent\": \"40%\" }, {
	// \"categoryCode\": \"FoodDining\", \"categoryLabel\": \"Food & Dining\",
	// \"categoryImage\": \"e909\", \"colorCode\": \"FF5544\", \"currency\":
	// \"SGD\", \"amount\": \"200.00\", \"formattedAmount\": \"200.00\",
	// \"percent\": \"0.2\", \"formattedPercent\": \"20%\" }, {
	// \"categoryCode\": \"Entertainment\", \"categoryLabel\":
	// \"Entertainment\", \"categoryImage\": \"e904\", \"colorCode\":
	// \"EE88CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"BillsUtilities\",
	// \"categoryLabel\": \"Bills & Utilities\", \"categoryImage\": \"e900\",
	// \"colorCode\": \"BB66CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"Transportation\",
	// \"categoryLabel\": \"Transportation\", \"categoryImage\": \"e916\",
	// \"colorCode\": \"8855DD\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"HealthFitness\",
	// \"categoryLabel\": \"Health & Fitness\", \"categoryImage\": \"e90c\",
	// \"colorCode\": \"909090\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" } ] }, { \"date\": \"2017-04-22\",
	// \"formattedDate\": \"April 2017\", \"amount\": \"1000.00\",
	// \"formattedAmount\": \"1,000.00\", \"categoryTotals\": [ {
	// \"categoryCode\": \"Shopping\", \"categoryLabel\": \"Shopping\",
	// \"categoryImage\": \"e914\", \"colorCode\": \"FFBB33\", \"currency\":
	// \"SGD\", \"amount\": \"400.00\", \"formattedAmount\": \"400.00\",
	// \"percent\": \"0.4\", \"formattedPercent\": \"40%\" }, {
	// \"categoryCode\": \"FoodDining\", \"categoryLabel\": \"Food & Dining\",
	// \"categoryImage\": \"e909\", \"colorCode\": \"FF5544\", \"currency\":
	// \"SGD\", \"amount\": \"200.00\", \"formattedAmount\": \"200.00\",
	// \"percent\": \"0.2\", \"formattedPercent\": \"20%\" }, {
	// \"categoryCode\": \"Entertainment\", \"categoryLabel\":
	// \"Entertainment\", \"categoryImage\": \"e904\", \"colorCode\":
	// \"EE88CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"BillsUtilities\",
	// \"categoryLabel\": \"Bills & Utilities\", \"categoryImage\": \"e900\",
	// \"colorCode\": \"BB66CC\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"Transportation\",
	// \"categoryLabel\": \"Transportation\", \"categoryImage\": \"e916\",
	// \"colorCode\": \"8855DD\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" }, { \"categoryCode\": \"HealthFitness\",
	// \"categoryLabel\": \"Health & Fitness\", \"categoryImage\": \"e90c\",
	// \"colorCode\": \"909090\", \"currency\": \"SGD\", \"amount\": \"100.00\",
	// \"formattedAmount\": \"100.00\", \"percent\": \"0.1\",
	// \"formattedPercent\": \"10%\" } ] } ] } ";
	// CardExpenseResponseDTO card =mapper.readValue(jsonInString2,
	// CardExpenseResponseDTO.class);
	// System.out.println(card);
	// String prettyCard =
	// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(card);
	// System.out.println(prettyCard);

	// SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	// String time = sdf.format(Calendar.getInstance());
}
