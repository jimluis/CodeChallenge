package com.luisfelipejimenez.transportmanager;


import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.vo.MessageVO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TransportManager implements ITransportManager
{


	protected String queueName = null;
	private String host = null;
	private int portNumber;
	private Connection connection = null;
	protected static Channel channel = null;
	private Receiver consumer = null;
	Properties properties = null;
	private static Logger logger = LogManager.getLogger(TransportManager.class);


	public TransportManager(String propFileName) {
		initProperties(propFileName);
	}

	public TransportManager() {
	}
	
	@Override
	public void sender(MessageVO msg) 
	{
		logger.debug("sender() - starts");
		try {
			Send.sender(msg, this);
		} catch (Exception e) {
			logger.error("An exception occurred while sending a message: ",e);
		}

	}

	
	public void initSenderConfig()
	{
		logger.info("initSenderConfig() - Starts" );
		
		try 
		{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setPort(portNumber);
			    
		    connection = factory.newConnection();
		    channel = connection.createChannel();
		    
		    channel.queueDeclare(queueName, false, false, false, null);
			    
		} catch (Exception e) {
			logger.error("initSenderConfig() - An exception occurred while initializing sender config",e );
		}
	
		logger.info("initSenderConfig() - Ends" );
	}
	
	
	public void initReceiverConfig()
	{
		try 
		{
			logger.info("initReceiverConfig() - Before ConnectionFactory" );
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setPort(portNumber);
			
			connection = factory.newConnection();
			channel = connection.createChannel();
			    			
		} catch (Exception e) {
			logger.error("initReceiverConfig() - Exception: ",e );
		}
	}
	
	public void initConsumer()
	{
		try 
		{
			logger.info("initConsumer() - Before generating listener" );
			
			channel.basicConsume(queueName, false, consumer);
			
			    			
		} catch (Exception e) {
			logger.error("initConsumer() - Exception: ",e );
		}
	}
	
	public void initProperties(String propFileName)
	{
		logger.info("initProperties() - Starts - propFileName: "+propFileName);
		FileInputStream fileIn = null;
		
		try 
		{
			if(propFileName != null && !propFileName.isEmpty())
			{
				properties = new Properties();
				fileIn = new FileInputStream(propFileName);
				
				properties.load(fileIn);
				
				if(properties.getProperty("host") != null && !properties.getProperty("host").isEmpty())
					host = properties.getProperty("host");
				else
					logger.info("initProperties() - host not found");
				
				if(properties.getProperty("portNumber") != null && !properties.getProperty("portNumber").isEmpty())
					portNumber = Integer.valueOf(properties.getProperty("portNumber"));
				else
					logger.info("initProperties() - portNumber not found");
				
				if(properties.getProperty("queueName") != null && !properties.getProperty("queueName").isEmpty())
					queueName = properties.getProperty("queueName");
				else
					logger.info("initProperties() - queueName not found");
			}				
			
		} catch (Exception e) {
			logger.error("initProperties() - Not valid queueName",e);
		}
		
		logger.info("initSenderMq() - Ends" );
		
	}


	protected String getQueueName() {
		return queueName;
	}

	protected Channel getChannel() {
		return channel;
	}

	@Override
	public void setConsumer(Receiver consumer) {
		this.consumer = consumer;
		
	}

}
