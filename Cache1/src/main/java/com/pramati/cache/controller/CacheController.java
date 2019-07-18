package com.pramati.cache.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.cache.service.CacheService;

@RestController
@RequestMapping("/cache")
public class CacheController {
	@Autowired
	CacheService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
	

	@RequestMapping(value="/get/{key}", method = RequestMethod.GET,produces={"plain/text"})
    public String getCacheValue(@PathVariable("key") String key,HttpServletRequest request, HttpServletResponse response){
		LOGGER.info("CacheController inside getCache ::key : "+key);
		String value = service.getCache(key);
		return value;
    }

}
