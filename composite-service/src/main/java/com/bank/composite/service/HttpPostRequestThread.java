package com.bank.composite.service;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpPostRequestThread implements Callable{

	private String URL;
	private Object ob;
	
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public Object getOb() {
		return ob;
	}
	public void setOb(Object ob) {
		this.ob = ob;
	}
	HttpPostRequestThread(String URL, Object ob){
		this.URL=URL;
		this.ob=ob;
	}
	@Override
	public Integer call() {
		// TODO Auto-generated method stub
		int status=0;
		try {
			status=requestPost(this.URL,this.ob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	}

public int requestPost(String url, Object ob) throws ClientProtocolException, IOException {
		
		HttpClient client= HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonMessage = mapper.writeValueAsString(ob);
	    StringEntity stringEntity=new StringEntity(jsonMessage);
	    stringEntity.setContentType("application/json");
	    stringEntity.setContentEncoding("UTF-8");
	    httpPost.setEntity(stringEntity);
	    HttpResponse response = client.execute(httpPost);
	    
	    return response.getStatusLine().getStatusCode();
	}


}
