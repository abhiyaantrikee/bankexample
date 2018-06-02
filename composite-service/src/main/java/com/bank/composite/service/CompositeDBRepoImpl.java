package com.bank.composite.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Qualifier("compositeDBRepoImpl")
public class CompositeDBRepoImpl implements CompositeRepository {

	/*@Autowired
	private MongoOperations operations;
*/
	@Override
	public String create(Composite composite) {
		
		ExecutorService executor=Executors.newFixedThreadPool(3);
		List<Future<Integer>> future= new ArrayList<Future<Integer>>();
		HttpPostRequestThread request1=new HttpPostRequestThread("http://localhost:3333/users",composite.getUserDTO());
		HttpPostRequestThread request2=new HttpPostRequestThread("http://localhost:2222/accounts",composite.getAccountDTO());
		Future<Integer> f1= executor.submit(request1);
		future.add(f1);
		Future<Integer> f2= executor.submit(request2);
		future.add(f2);
		
		return "OK";
	}
}
