package com.bank.eventhandler.service;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
@EnableBinding(Sink.class)
public class MessageListener {
  

	@Autowired
	MessageSender messageSender;

	protected Logger logger = Logger
			.getLogger(MessageListener.class.getName());
	
   /**
   * Handles incoming UserCreatedEvents. 
   * 
   *  Using the conditional {@link StreamListener} from 
   * https://github.com/spring-cloud/spring-cloud-stream/blob/master/spring-cloud-stream-core-docs/src/main/asciidoc/spring-cloud-stream-overview.adoc
   * in a way close to what Axion
   *  would do (see e.g. https://dturanski.wordpress.com/2017/03/26/spring-cloud-stream-for-event-driven-architectures/)
   */
  @StreamListener(Sink.INPUT)
  @Transactional
  public void userCreatedEventReceived( Map<String,String> message) throws JsonParseException, JsonMappingException, IOException {

	  if(message.get("servicename").equals("UserCreatedEvent")) {
		  Message<UserDTO> obj = new ObjectMapper().readValue(message.get("data"), new TypeReference<Message<UserDTO>>(){});
		  UserDTO user = (UserDTO) obj.getPayload();
		  String traceId=obj.getTraceId();
		  String id=user.getId();
		  HttpClient client= HttpClients.createDefault();
		  HttpPatch httpPatch = new HttpPatch("http://localhost:2222/accounts/"+traceId);
		  StringEntity stringEntity=new StringEntity(user.getId());
		  stringEntity.setContentType("application/json");
		  stringEntity.setContentEncoding("UTF-8");
		  httpPatch.setEntity(stringEntity);
		  HttpResponse response = client.execute(httpPatch);
		  logger.info("Message Listener Completed Task with response :"+response.getStatusLine().getStatusCode());
		  
		  ResponseBody responsebody=new ResponseBody(HttpStatus.SC_CREATED,"Account has be generated"+id);
		  
		  Message<ResponseBody> m=new Message<ResponseBody>("accountCreated",responsebody);
		  messageSender.send(m);  
		  logger.info("Message Has been sent : accountCreated");
	  }
  }

}
