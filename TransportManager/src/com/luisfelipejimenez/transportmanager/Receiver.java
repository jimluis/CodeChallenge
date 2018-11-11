package com.luisfelipejimenez.transportmanager;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.vo.MessageVO;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Receiver extends DefaultConsumer 
{
	private static Logger logger = LogManager.getLogger(Receiver.class);
	int counter = 0;


	public Receiver() {
		super(TransportManager.channel);
	}
	
	  public void handleDelivery(String consumerTag, Envelope envelope,
	            BasicProperties properties, byte[] body) throws java.io.IOException 
	  {
		  boolean isMessageProcessed = false;
		  
		  try 
		  {
		  	  MessageVO message = (MessageVO) SerializationUtils.deserialize(body);
		  	  counter++;

		  	  isMessageProcessed = processMessage(message);
		  	  logger.info("handleDelivery() - isMessageProcessed: "+isMessageProcessed);

		  	  if(isMessageProcessed)
		  		  TransportManager.channel.basicAck(envelope.getDeliveryTag(), false);
		  	  else 
		  		  logger.info("handleDelivery() - Message with id: "+message.getIdSeq()+" was not processed");
		    
		} catch (Exception e) {
			logger.error("handleDelivery() - Exception: ",e);
		}

	  }


	  public boolean processMessage(MessageVO message) {
		return false;
	  };
	  
}
