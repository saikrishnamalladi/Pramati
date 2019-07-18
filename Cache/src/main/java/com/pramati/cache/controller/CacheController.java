package com.pramati.cache.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.cache.service.CacheService;

@RestController
@RequestMapping("/cache")
public class CacheController {
	@Autowired
	CacheService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
	
	@RequestMapping(value="/set/{key}", method = RequestMethod.POST,produces={"application/json","application/xml"})
    public ResponseEntity<String> setCacheValue(@PathVariable("key") String key,@RequestParam("value") String value,HttpServletRequest request, HttpServletResponse response){
		LOGGER.info("CacheController inside setCache ::key : "+key+" : value : "+value);
		service.setCache(key, value);
		return new ResponseEntity<>(HttpStatus.OK);
    }
	@RequestMapping(value="/get/{key}", method = RequestMethod.GET,produces={"plain/text"})
    public String getCacheValue(@PathVariable("key") String key,HttpServletRequest request, HttpServletResponse response){
		LOGGER.info("CacheController inside getCache ::key : "+key);
		String value = service.getCache(key);
		return value;
    }

}
