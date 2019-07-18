package com.pramati.kvstore.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.pramati.kvstore.task.StoreTask;

@Component
public class Server {
	
	@Value( "${server.port}" )
	private int port;
	
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	ApplicationContext context;
	
	private ServerSocket serverSocket;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);


	@PostConstruct
	public void start() {
		try {
			LOGGER.info("Configured port for TCP Server : "+port);
			serverSocket = new ServerSocket(port);
			LOGGER.info("TCP Server started on the port : "+port);
			while (true) {
				LOGGER.info("TCP Server waiting for the request");
				Socket socket = serverSocket.accept();
			    StoreTask storeTask = (StoreTask) context.getBean("storeTask");
			    storeTask.setSocket(socket);
			    taskExecutor.execute(storeTask);
			}
		} catch (IOException e) {
			LOGGER.error("Exception in TCP Server:::"+e.getMessage());
		}
	}

	@PreDestroy
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
