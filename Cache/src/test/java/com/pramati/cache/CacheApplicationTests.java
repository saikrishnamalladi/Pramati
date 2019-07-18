package com.pramati.cache;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pramati.cache.controller.CacheController;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CacheApplication.class})
public class CacheApplicationTests {

	@Autowired
	CacheController controller;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	
	@Test
	public void cacheSet() {
		ResponseEntity<String> result =  controller.setCacheValue("test", "sample", request, response);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void cacheGet() {
		String result = controller.getCacheValue("test", request, response);
		assertEquals(result, "sample");

	}
	

}
