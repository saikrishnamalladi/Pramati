package com.pramati.kvstore.store;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pramati.kvstore.task.StoreTask;

@Component
public class KVStore {
	
	private Map<String,String> store= new ConcurrentHashMap<>();
	
	@Value("${store.fileName}")
    private String fileName;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreTask.class);
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void loadStore() {
	    FileInputStream fis;
	    ObjectInputStream ois;
		try {
			fis = new FileInputStream(fileName);
			LOGGER.error("loading store from file::::"+fileName);
			ois=new ObjectInputStream(fis);
	        store=(Map<String,String>)ois.readObject();
	        ois.close();
	        fis.close();
		} catch (Exception e) {
			LOGGER.error("Exception while loading store from file::::"+e.getMessage());
		} 
	}
	
	public void storeEntry(String key,String value) {
		store.put(key, value);
		LOGGER.info("store values after storing entry::::"+store);
	}
	
	public String getValue(String key) {
		return store.get(key);
	}
	
	public Map<String,String> getStore() {
		return store;
	}

}
