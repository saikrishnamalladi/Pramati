package com.pramati.kvstore.store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StoreWriter {
	
	@Value( "${store.fileName}" )
	private String fileName;
	
	@Autowired
	KVStore kvstore;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreWriter.class);

    @Scheduled(fixedRate = 10000)
	public void writeToFile() {
    	Map<String,String> store = kvstore.getStore();
		LOGGER.info("write store to file : "+store);
		if (store == null || store.size() == 0)
			return;
	    File file = new File(fileName);
		LOGGER.info("file path and name: "+file.getPath());
	    FileOutputStream fos;
	    ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(file);
			oos=new ObjectOutputStream(fos);
			oos.writeObject(store);
	        oos.flush();
	        oos.close();
	        fos.close();
		} catch (Exception e) {
			LOGGER.error("Exception while writing store to writer:::"+e.getMessage());
		} 
	}

}
