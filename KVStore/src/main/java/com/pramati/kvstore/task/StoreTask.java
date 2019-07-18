package com.pramati.kvstore.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pramati.kvstore.store.KVStore;

@Component
@Scope("prototype")
public class StoreTask implements Runnable {

	private Socket clientSocket;
	@Autowired
	KVStore store;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreTask.class);

	public void setSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	@Override
	public void run() {
		try {
			LOGGER.info("Store task started");
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //convert ObjectInputStream object to String
            String message = reader.readLine();
            LOGGER.info("Store task Message Received: "+message);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);
			String[] entry = message.split("=");
			String response="";
			if (entry.length == 1) {
				response = store.getValue(entry[0]);
			}else {
				response = entry[1];
				store.storeEntry(entry[0], entry[1]);
			}
            //write object to Socket
			writer.println(response);
            LOGGER.info("Store task Response Sent: "+response);
		} catch (Exception e) {
            LOGGER.error("Store task Exception "+e.getMessage());
		}
	}

}
