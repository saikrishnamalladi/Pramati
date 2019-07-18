package com.pramati.kvstore.replicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pramati.kvstore.store.StoreWriter;

@Component
@Scope("prototype")
public class ReplicateTask implements Runnable{
	
	@Autowired
	ConnectionPool connectionPool;
	
	@Autowired
	StoreWriter writer;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicateTask.class);
	private String key;
	private String value;
    
	public void run() {
		LOGGER.info("Replication start::::");
		Client client = connectionPool.getClient();
		String response = client.sendMessage(key+"="+value);
	    connectionPool.releaseClient(client);
	    LOGGER.info("Replication end::::"+response);
	    writer.writeToFile();
	}

	public void setKey(String key) {
		this.key = key;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
