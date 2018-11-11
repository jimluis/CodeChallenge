package com.luisfelipejimenez.transportmanager;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.vo.MessageVO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Receiver extends DefaultConsumer 
{
	private static Logger logger = LogManager.getLogger(Receiver.class);
	
	public static TransportService transp = new TransportService();
	int counter = 0;
	
	public Receiver() 
	{
		super(transp.getChannel());
		logger.info("Receiver - channel: "+transp.getChannel());
	}
		
	public Receiver(Channel channel) {
		super(channel);

	}
	
	  public void handleDelivery(String consumerTag, Envelope envelope,
	            BasicProperties properties, byte[] body) throws java.io.IOException 
	  {
		  logger.info("Receiver - Received");
		  
		  try 
		  {
			  
		  	  MessageVO message = (MessageVO) SerializationUtils.deserialize(body);
		  	  counter++;

		  	  processMessage(message);
		  	  logger.info("Receiver - counter ---"+counter);
		  	  logger.info("Receiver - Message counter: "+counter+" - Thread name: "+Thread.currentThread().getName());

		  	  getChannel().basicAck(envelope.getDeliveryTag(), false);
		    
		} catch (Exception e) {
			logger.error("ServiceConsumer - Exception: ",e);
		}

	  }


	  public void processMessage(MessageVO message) {
		  logger.info("processMessage() ---");
	  };
	  
}
