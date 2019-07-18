package com.pramati.kvstore.store;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.pramati.kvstore.replicate.ReplicateTask;
import com.pramati.kvstore.task.StoreTask;

@Component
public class KVStore {
	
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	ReplicateTask replicate;
	
	private Map<String,String> store= new ConcurrentHashMap<>();
	
	@Value("classpath:${store.fileName}")
    private Resource resource;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreTask.class);
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void loadStore() {
	    FileInputStream fis;
	    ObjectInputStream ois;
		try {
			fis = new FileInputStream(resource.getFile());
			LOGGER.error("loading store from file::::"+resource.getFilename());
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
		ReplicateTask replicateTask = (ReplicateTask) context.getBean("replicateTask");
		replicateTask.setKey(key);
		replicateTask.setValue(value);
	    taskExecutor.execute(replicateTask);
	}
	
	public String getValue(String key) {
		return store.get(key);
	}
	
	
	public Map<String,String> getStore() {
		return store;
	}
}
