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

public class TransportService implements ITransportService
{


	private static String queueName = null;
	private static String host = null;
	private static int portNumber;
	private static Connection connection = null;
	private static Channel channel = null;
	private Receiver consumer = null;
	Properties properties = null;

	private static Logger logger = LogManager.getLogger(TransportService.class);


	public TransportService(String propFileName) {
		initProperties(propFileName);
	}

	public TransportService() {
	}
	
	@Override
	public void sender(MessageVO msg) 
	{
		logger.info("sending");
		try {
			sendMessage(msg);
		} catch (Exception e) {
			logger.error("Exception: ",e);
		}

	}

	private static void sendMessage(MessageVO msg) throws Exception 
	{
		logger.info("sendMessage() - Starts" );
	
		try 
		{
		  	byte[] data = SerializationUtils.serialize(msg);
		  	getChannel().basicPublish("", queueName, null, data);
		    logger.info("sendMessage() - Message sent: "+ msg.toString());
//				TransportService.channel.close();
//			    TransportService.connection.close();
			    
		} catch (Exception e) {
			logger.error("An exception occurred while sending a message", e );
			
		}
		
		logger.info("sendMessage() - Ends" );
	    
	}
	
	public void initSenderMq()
	{
		logger.info("initSenderMq() - Starts" );
		
		try 
		{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setPort(portNumber);
			    
		    connection = factory.newConnection();
		    channel = connection.createChannel();
		    setChannel(channel);
		    
		    channel.queueDeclare(queueName, false, false, false, null);
			    
		} catch (Exception e) {
			logger.error("An exception occurred while initializing senderMQ config",e );
		}
	
		logger.info("initSenderMq() - Ends" );
	}
	
	
	public void initReceiverMq()
	{
		try 
		{
			logger.info("initReceiverMq - Before ConnectionFactory" );
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setPort(portNumber);
			
			connection = factory.newConnection();
			channel = connection.createChannel();

			    			
		} catch (Exception e) {
			logger.error("Exception: ",e );
		}
	}
	
	public void initConsumer()
	{
		try 
		{
			logger.info("initReceiverMq - Before ConnectionFactory" );
			
			channel.basicConsume(queueName, false, consumer);
			
			    			
		} catch (Exception e) {
			logger.error("Exception: ",e );
		}
	}
	
	public void initProperties(String propFileName)
	{
		logger.info("initProperties() - Starts - propFileName: "+propFileName);
		FileInputStream fileIn = null;
		
		try 
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
						
			
		} catch (Exception e) {
			logger.error("initProperties() - Not valid queueName",e);
		}
		
		logger.info("initSenderMq() - Ends" );
		
	}

	@Override
	public void receiver(MessageVO msg) {
		System.out.println("receiver() - Ends" );
		
	}

	@Override
	public void processMessage(MessageVO msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReceiver(Receiver consumer) {
		setConsumer(consumer);
		
	}

	
	public Receiver getConsumer() {
		return consumer;
	}

	public void setConsumer(Receiver consumer) {
		this.consumer = consumer;
	}

	public static String getQueueName() {
		return queueName;
	}

	public static void setQueueName(String queueName) {
		TransportService.queueName = queueName;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		TransportService.host = host;
	}

	public static int getPortNumber() {
		return portNumber;
	}

	public static void setPortNumber(int portNumber) {
		TransportService.portNumber = portNumber;
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		TransportService.connection = connection;
	}

	public static Channel getChannel() {
		return channel;
	}

	public static void setChannel(Channel channel) {
		TransportService.channel = channel;
	}

	
}
