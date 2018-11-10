package com.luisfelipejimenez.transportmanager;

//import java.lang.reflect.Method;

import org.apache.commons.lang3.SerializationUtils;

import com.luisfelipejimenez.vo.MessageVO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Receiver extends DefaultConsumer 
{
	public static TransportService transp = new TransportService();
	int counter = 0;
	
	public Receiver() 
	{
		super(transp.getChannel());
		System.out.println("Receiver - channel: "+transp.getChannel());
	}
		
	public Receiver(Channel channel) {
		super(channel);

	}
	
	  public void handleDelivery(String consumerTag, Envelope envelope,
	            BasicProperties properties, byte[] body) throws java.io.IOException 
	  {
		  
		  try 
		  {
			  System.out.println("ServiceConsumer - Received ---");
		  	  MessageVO message = (MessageVO) SerializationUtils.deserialize(body);
		  	  counter++;
		  	  processMessage(message);
		  	  
		  	  System.out.println("ServiceConsumer - Message counter: "+counter+" - Thread name: "+Thread.currentThread().getName());

		  	  getChannel().basicAck(envelope.getDeliveryTag(), false);
		    
		} catch (Exception e) {
			 System.out.println("ServiceConsumer - Exception: "+e);
		}

	  }


	  public void processMessage(MessageVO message) {
		  
	  };
	  
}
