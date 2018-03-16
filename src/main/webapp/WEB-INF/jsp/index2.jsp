<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedTrack - Admin</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/ezmodal.min.css"/>">

<script src="<c:url value="/resources/js/jquery-2.2.0.min.js"/>"></script>
<script src="<c:url value="/resources/js/ezmodal.min.js"/>"></script>
<script>
var currentlyselectedValue;
var currentAppid;
var currentCntryid;
var currentBizunitid;
var currentmode;
$(document).ready(function()
{	
	currentmode='mapping';
	$("#loadingdiv").hide();
	$("#addnewappiddiv").hide();

	$("#updateappmappingid").addClass('selected');
    $("#addnewappid").removeClass('selected');
    
	$(".accordion h3:first").addClass("active");
	//$(".accordion table:not(:first)").hide();

	$(".accordion h3").click(function()
	{
		$(this).next("table").slideToggle("slow").siblings("table:visible").slideUp("slow");
		$(this).toggleClass("active");
		$(this).siblings("h3").removeClass("active");
	});

	/*
	$(".menu2 a").append("<em></em>");
	$(".menu2 a").hover(function() 
	{
		$(this).find("em").animate({opacity: "show", top: "-75"}, "slow");
		var hoverText = $(this).attr("title");
	    $(this).find("em").text(hoverText);
	}, 
	function() 
	{
		$(this).find("em").animate({opacity: "hide", top: "-85"}, "fast");
	});
	*/
	$("#addnewappid").click(function(event)
	{
		event.preventDefault();
		$('input:checkbox').attr('checked',false);
		$('#mapoldappiddiv').fadeOut('slow' ,function()
		{
			currentmode='addition';
		    $('#addnewappiddiv').fadeIn('slow');
		});

		$("#addnewappid").addClass('selected');
	    $("#updateappmappingid").removeClass('selected');
	});

	$("#updateappmappingid").click(function(event)
	{
		event.preventDefault();
		$('input:checkbox').attr('checked',false);
		$('#addnewappiddiv').fadeOut('slow' ,function()
		{
			currentmode='mapping';
			$('#mapoldappiddiv').fadeIn('slow');
		});

		$("#updateappmappingid").addClass('selected');
	    $("#addnewappid").removeClass('selected');
	});
	
	$('#countryselectbox').change(function()
	{
	    $('input:checkbox').attr('checked',false);
	    var tempvar=$('#countryselectbox :selected').val();
	    if(!(tempvar=="" || tempvar==null || tempvar==undefined || tempvar.length<=0))
		{
	    	var temparr=tempvar.split("--");
		    try
		    {
		    	var countryarrids=temparr[1].split(",");
			    $.each(countryarrids, function( index, value ) 
			    {
			    	document.getElementById("cntry_"+value).checked=true;
			    });

			    var bizunitsids=temparr[2].split(",");
			    $.each(bizunitsids, function( index, value ) 
			    {
			    	document.getElementById("bizunit_"+value).checked=true;
			    });
			}
			catch(err)
			{

			}
		}
	});	
	
	$('.menu2 a').click(function(event) 
	{
	   event.preventDefault();
	   if('mapping'==currentmode)
	   {
		   var tempvar=$('#countryselectbox :selected').val();
		   var temparr=tempvar.split("--");
		   var selectedapplicationid=temparr[0];

		   if(selectedapplicationid=="" || selectedapplicationid==null || selectedapplicationid==undefined || selectedapplicationid.length<=0)
		   {
			   alert("Please select application.");
			   return;
		   }
		   
		   $('#loadingdiv').fadeIn("slow");
		   
		   var checkedcountries = [];
		   $('.countrycboxcss:checked').each(function() 
		   {
			   checkedcountries.push($(this).val());
		   });

		   var checkedbizunits = [];
		   $('.bizunitcboxcss:checked').each(function() 
		   {
			   checkedbizunits.push($(this).val());
		   });

		   if( (checkedcountries.length>0 && checkedbizunits.length<=0) || (checkedcountries.length<=0 && checkedbizunits.length>0) )
		   {
				if(checkedcountries.length==0)
				{
					alert("Please select countries.");
					return;
				}
				if(checkedbizunits.length==0)
				{
					alert("Please select business units.");
					return;
				}	
		   }
		   $('body').css('cursor', 'wait');

		   //alert("./dssservice?checkedcountries="+checkedcountries+"&checkedbizunits="+checkedbizunits+"&selectedapplicationid="+selectedapplicationid+"&ename=updateApplication");
		   //return;
		   $.ajax("./dssservice?checkedcountries="+checkedcountries+"&checkedbizunits="+checkedbizunits+"&selectedapplicationid="+selectedapplicationid+"&ename=updateApplication", 
		   {
		      success: function(data) 
		      {
		    	$('#loadingdiv').fadeOut("slow"); 
		    	$('body').css('cursor', 'auto');
		    	var ele=document.getElementById("countryselectbox");
		  	    for(var ii=0; ii<ele.length; ii++)
		  		{
		  	      if(ele.options[ii].value==tempvar) 
		  		  {
		  	        ele.options[ii].value=selectedapplicationid+"--"+checkedcountries+"--"+checkedbizunits;
		  	      }
		  		}
		        //alert("Application has been mapped, successfully.");
		        $("#cstmalertheader").html("Success") ;
			    $("#cstmalertbody").css("color","green");
			    $("#cstmalertbody").html("Application has been mapped, successfully.") ;
			    $("#cstmalertbtn").html("OK") ;
			    $('#mymodal').ezmodal('show');
		      },
		      error: function() 
		      {
		    	  //alert("error");
		    	  $("#cstmalertheader").html("Failure") ;
			      $("#cstmalertbody").css("color","red");
			      $("#cstmalertbody").html("Error") ;
			      $("#cstmalertbtn").html("OK") ;
			      $('#mymodal').ezmodal('show');
		      }
		   });
	   }
	   else if('addition'==currentmode)
	   {
		   var enteredapplicationname= $("#newappdtextid").val();  
		   if(enteredapplicationname==null || enteredapplicationname==undefined || enteredapplicationname=="" || enteredapplicationname.length<=0)
		   {
				alert("Please enter application name.");
				return;
		   }
		   
		   $('#loadingdiv').fadeIn("slow");
		   var checkedcountries = [];
		   $('.countrycboxcss:checked').each(function() 
		   {
			   checkedcountries.push($(this).val());
		   });

		   var checkedbizunits = [];
		   $('.bizunitcboxcss:checked').each(function() 
		   {
			   checkedbizunits.push($(this).val());
		   });

		   if( (checkedcountries.length>0 && checkedbizunits.length<=0) || (checkedcountries.length<=0 && checkedbizunits.length>0) )
		   {
				if(checkedcountries.length==0)
				{
					alert("Please select countries.");
					return;
				}
				if(checkedbizunits.length==0)
				{
					alert("Please select business units.");
					return;
				}	
		   }
		   $('body').css('cursor', 'wait');

		   //alert("./dssservice?checkedcountries="+checkedcountries+"&checkedbizunits="+checkedbizunits+"&enteredapplicationname="+enteredapplicationname+"&ename=addApplicationname");
		   //return;
		   $.ajax("./dssservice?checkedcountries="+checkedcountries+"&checkedbizunits="+checkedbizunits+"&enteredapplicationname="+enteredapplicationname+"&ename=addApplicationname", 
		   {
		      success: function(data) 
		      {
		    	$('#loadingdiv').fadeOut("slow");
		    	$('body').css('cursor', 'auto'); 
		    	var constructedvalueis=data+"--"+checkedcountries+"--"+checkedbizunits;
		    	$("#countryselectbox").append('<option value="'+constructedvalueis+'">'+enteredapplicationname+'</option>');
		    	alert("Application has been added, successfully.");
		      },
		      error: function() 
		      {
		    	  alert("error");
		      }
		   });
	   }
	    
	   
	  
	});
	
});
</script>

<style type="text/css">
a
{
    color: blue;   
}
.selected 
{
     color: red;   
}

/*Fixed header and footer : START*/
html, body 
{
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
}
header
{
	//background: #000 none repeat-x scroll 0 0;
	background: #000 url("/resources/images/mixed.png") no-repeat scroll left center;
	//background-image: url("./images/dbs.png") repeat-x scroll 0 0;
    border-bottom: 9px solid #c00;
    height: 85px;
    
    width: 100%;
    //height: 90px;
    //background-color: #666666;
    position: fixed;
    top: 0;
}
#content 
{
    width: 90%;
    margin: 0 auto;
    padding: 110px 0;
}
footer 
{
    width: 100%;
    height: 40px;
    background-color: #000;
    position: fixed;
    bottom: 0;
}
/*Fixed header and footer : END*/

a.disabled { color:gray; }

/*Onmouse over : START*/
.menu2 
{
	margin: 10px 0 0;
	padding: 0;
	list-style: none;
}
.menu2 li 
{
	padding: 0;
	margin: 0 0px;
	float: left;
	position: relative;
	text-align: center;
}
.menu2 a 
{
	padding: 7px 0px;
	display: block;
	color: #000000;
	width: 144px;
	text-decoration: none;
	font-weight: bold;
	background: url(<c:url value="resources/images/button.gif" />) no-repeat center center;
}
.menu2 li em 
{
	font-weight: normal;
	background: url(<c:url value="resources/images/hover.gif" />) no-repeat;
	width: 180px;
	height: 45px;
	position: absolute;
	top: -85px;
	left: -15px;
	text-align: center;
	padding: 20px 12px 10px;
	font-style: normal;
	z-index: 2;
	display: none;
}
/*Onmouse over : END*/

.accordion 
{
	width: 800px;
	border-bottom: solid 1px #c4c4c4;
}
.accordion h3 
{
	background: #e9e7e7 right -51px;
	padding: 7px 15px;
	margin: 0;
	
	font-weight: bold;
	font-family: "Arial";
	font-style: normal;
	font-size: 13px;
	
	border: solid 1px #c4c4c4;
	border-bottom: none;
	cursor: pointer;
}
.accordion h3:hover 
{
	background-color: #e3e2e2;
}
.accordion h3.active 
{
	background-position: right 5px;
}
.accordion table
{
	background: #f7f7f7;
	margin: 0;
	padding: 10px 15px 20px;
	border-left: solid 1px #c4c4c4;
	border-right: solid 1px #c4c4c4;
}

.accordion table label
{
	font-family: "Arial";
	font-style: normal;
	font-size: 12px;
}
</style>
</head>

<body>
    <!-- <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>
 --> 
		<header class="custem_header_bg"><img src="<c:url value="/resources/images/mixed.png" />" class="img-responsive">
			<p></p>
		</header>
		
		<div id="content">	 
			<c:url value="/resources/images/mixed.png" />
			 <nav>
			  	<a href="#" id="updateappmappingid" style="font-weight: bold; font-size: 13px;">Update existing aplication</a> |
			  	<a href="#" id="addnewappid" style="font-weight: bold; font-size: 13px;">Add new application</a> | 
			  	<a href="./dssservice?ename=getallappsStatus" style="font-weight: bold; font-size: 13px;">Update application status</a>   
			  	<!-- <a href="./dssservice?ename=viewhealthCheck" style="font-weight: bold; font-size: 13px;">View current health check</a> | 
			  	<a href="./dssservice?ename=getAllMQTTAcks" style="font-weight: bold; font-size: 13px;">MQTT Acknowledgements</a> -->
			</nav> 
			<form name="myform" id="myform">
			<section id="main">
				<article>
					
					<div style="margin-top: 22px; margin-bottom: 21px;" class="selectionboxdiv" id="mapoldappiddiv">
						<label style="font-size: 13px">Map existing applications :</label>&nbsp;&nbsp;&nbsp;
						<select style="width: 185px; height: 26px;" id="countryselectbox">
							<option value="" selected="selected">Please select application</option>
							<c:forEach items="${listofapptypes}" var="listofapptypes">
						        <option value="${listofapptypes.value}">${listofapptypes.key}</option>
						    </c:forEach>
						</select>
					</div>
					
					<div style="margin-top: 22px; margin-bottom: 21px;" class="selectionboxdiv" id="addnewappiddiv">
						<label style="font-size: 13px">Add new application :</label>&nbsp;&nbsp;&nbsp;
						<input type="text" style="width: 185px; height: 26px;"  id="newappdtextid" name="newappdtextid"/>
					</div>
					
					<div class="accordion">
						<h3>Countries</h3>
						
							<table style="width: 100%; height: 100%;">
								<tr><td><div style="padding-top: 15px;"></div></td><td></td><td></td></tr>
								<c:set var="i" scope="page" value="2"/>
								<c:forEach items="${listofcountries}" var="listofcountries">
								<c:set var="i" value="${i + 1}" scope="page"/>
								<c:choose> 
								  <c:when test="${i%3 == 0}">
								
							  	      <tr>
								        <td style="padding-left: 30px; padding-top: 5px;"><label><input type="checkbox" name="cntry_${listofcountries.key}" id="cntry_${listofcountries.key}" class="countrycboxcss" value="${listofcountries.key}">  &nbsp;${listofcountries.value}</label></td>
								  </c:when>
								  <c:otherwise>
								        <td style="padding-left: 30px; padding-top: 5px;""><label><input type="checkbox" name="cntry_${listofcountries.key}" id="cntry_${listofcountries.key}" class="countrycboxcss" value="${listofcountries.key}">  &nbsp;${listofcountries.value}</label></td>
								  </c:otherwise>
								</c:choose>
								</c:forEach>
								<tr><td><div style="padding-top: 15px;"></div></td><td></td><td></td></tr>
							</table>
					</div>
					
					<div style="padding-top: 12px;padding-bottom: 12px;"></div>
					
					<div class="accordion">
						<h3>Business Units</h3>
							<table style="width: 100%; height: 100%;">
								<tr><td><div style="padding-top: 15px;"></div></td><td></td><td></td></tr>
								<c:set var="i" scope="page" value="2"/>
								<c:forEach items="${listofbizunits}" var="listofbizunits">
								<c:set var="i" value="${i + 1}" scope="page"/>
								<c:choose> 
								  <c:when test="${i%3 == 0}">
								  	<tr><td><div style="padding-top: 2px;padding-bottom: 2px;"></div></td><td></td><td></td></tr>
									<tr>
										<td style="padding-left: 30px; padding-top: 3px;"><label><input type="checkbox" name="bizunit_${listofbizunits.key}" id="bizunit_${listofbizunits.key}" class="bizunitcboxcss" value="${listofbizunits.key}">  &nbsp;${listofbizunits.value}</label></td>
								  </c:when>
								  <c:otherwise>
								  		<td style="padding-left: 30px; padding-top: 3px;"><label><input type="checkbox" name="bizunit_${listofbizunits.key}" id="bizunit_${listofbizunits.key}" class="bizunitcboxcss" value="${listofbizunits.key}">  &nbsp;${listofbizunits.value}</label></td>
								  </c:otherwise>
								  </c:choose>
								</c:forEach>
								<tr><td><div style="padding-top: 15px;"></div></td><td></td><td></td></tr>
							</table>
					</div>
					
					<div style="padding-top: 6px;padding-bottom: 6px;"></div>
					
					<ul class="menu2" id="submitbtnidul">
						<li>
							<a href="#" id="submitbtnid" title="Adds the application to the System.">Save</a>		
						</li>
					</ul>
					<div id="loadingdiv">
						<table>
							<tr>
								<td><img src="<c:url value="/resources/images/processbar.gif" />"/></td>
							</tr>
							<tr>
								<td><span style="color: red; padding-left: 6px;">Saving please wait ...</span></td>
							</tr>
						</table>
					</div>
					
				</article>
			</section>		
			<div id="mymodal" class="ezmodal">
                 <div class="ezmodal-container">
                     <div class="ezmodal-header" style="background-color: #e9e7e7;">
                         <div class="ezmodal-close" data-dismiss="ezmodal">x</div>
                         <span id="cstmalertheader">Success</span>
                     </div>
                     <div class="ezmodal-content">
                         <p style="color: green;" id="cstmalertbody">Application has been mapped, successfully.</p>
                     </div>
                     <div class="ezmodal-footer">
                         <button type="button" class="btn" data-dismiss="ezmodal" id="cstmalertbtn">OK</button>
                     </div>
                 </div>
             </div>
             	
			</form>
					
		</div>
		<footer>
			<p></p>
		</footer>


</body>
</html>