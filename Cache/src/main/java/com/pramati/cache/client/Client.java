package com.pramati.cache.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Client {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    @Value( "${client.host}" )
	private String host;
    
    @Value( "${client.port}" )
	private int port;
 
    @PostConstruct
    public void startConnection() {
        try {
			clientSocket = new Socket(host, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public String sendMessage(String msg) {
        out.println(msg);
        String resp;
		try {
			resp = in.readLine();
			return resp;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
        
    }
 
    public void stopConnection() {
        try {
			in.close();
			out.close();
	        clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
