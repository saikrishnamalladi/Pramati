package com.pramati.kvstore.replicate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Component
/** A Connection Pool with 5 Available Connections **/
public class ConnectionPool {

	@Autowired
	ApplicationContext context;
	
	@Value( "${client.pool.size}")
	private final int MAX_CONNECTIONS = 5;
	private BlockingQueue<Client> availableConnections = 
								new ArrayBlockingQueue<Client>(MAX_CONNECTIONS);
	
	@PostConstruct
	public void createConnections() {
		for (int i=0;i<MAX_CONNECTIONS;i++) {
			Client client = (Client)context.getBean("client");
			availableConnections.add(client);
		}
	}
	
	public Client getClient() {
		Client client;
		try {
			client = availableConnections.take();
			return client;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void releaseClient(Client client) {
		availableConnections.add(client);
	}
	
	@PreDestroy
	public void releaseConnections() {
		availableConnections.clear();
	}
	
}
