package com.pramati.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pramati.cache.client.Client;
import com.pramati.cache.client.ConnectionPool;
import com.pramati.cache.controller.CacheController;

@Component
public class CacheService {
	@Autowired
	ConnectionPool connectionPool;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
	
	public void setCache(String key,String value) {
		Client client = connectionPool.getClient();
	    String response = client.sendMessage(key+"="+value);
	    connectionPool.releaseClient(client);
		LOGGER.info("CacheService inside setCache ::value received : "+response);
	}
	
	public String getCache(String key) {
		Client client = connectionPool.getClient();
	    String response = client.sendMessage(key);
	    connectionPool.releaseClient(client);
		LOGGER.info("CacheService inside getCache ::value received : "+response);
	    return response;
	}
	

}
