package com.dbs.redtrack.web.utilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.util.property.AppConfig;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
 
//@Component
public class RedTrackCache implements InitializingBean {

	private static final Logger logger = Logger.getLogger(RedTrackCache.class);
 
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	AppConfig appConfig;
		
	@Resource(name="redtrackProperties")	  
	Properties redtrackProperties;
	 
	
	@Override
	public void afterPropertiesSet() throws RedTrackProcessingException {
		logger.info("afterPropertiesSet");
		
		long startTime = System.currentTimeMillis();		
		
		long endTime = System.currentTimeMillis();		
		logger.info("Initialization LOV List , Duration: " + (endTime - startTime) + "ms");
		logger.info("afterPropertiesSet");
	}

		
	@SuppressWarnings("unchecked")
	public  String getErrorMessageDescription(String errorCode)throws RedTrackProcessingException{
	
		logger.info("getErrorMessageDescription:errorCode:"+errorCode);
		Map<String, String> errorMessagesMap = null;
		Element element = getGenericCacheElement("whitelistproperties");
		if(null != element){			
			 errorMessagesMap = (HashMap<String, String>)element.getObjectValue();			
		}else{
			errorMessagesMap = getErrorMessages();
			populateRedTrackache("whitelistproperties", errorMessagesMap);
			
		}
		 if(null != errorMessagesMap.get(errorCode)){
	
			 return errorMessagesMap.get(errorCode);
		 }else{
			 return ">>>>Unable to retreive message from property file<<<";
		 }
		 
	}
	 
	public <K,V> void populateRedTrackache(K key, V value)throws RedTrackProcessingException {
		logger.info("populateRedTrackache: for key: "+key+" value:"+value );
		Cache genericCache = cacheManager.getCache("RedTrackCache");
		genericCache.put(new Element(key, value));
		  
	}

	public <K> Element getGenericCacheElement(K key)throws RedTrackProcessingException {
		Element element = null;
	
		logger.info("getGenericCacheElement:"+key);
		Cache genericCache = cacheManager.getCache("RedTrackCache");
	
		if(null != genericCache){
			element = genericCache.get(key);
		}
		return element;
	}
	
 
	private Map<String,String> getErrorMessages()throws RedTrackProcessingException {		
	 	
		Map<String,String> messagesMap = new HashMap<String, String>();
			Set<Entry<Object, Object>> redtrackpropertySet = null; 	
			 
			redtrackpropertySet = redtrackProperties.entrySet();
 
			for(Entry<Object, Object> entry : redtrackpropertySet){ 
				messagesMap.put((String) entry.getKey(), (String) entry.getValue());
			}
		 
		return messagesMap;
	}
	

 
}
