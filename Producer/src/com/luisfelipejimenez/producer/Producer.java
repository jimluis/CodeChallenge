package com.luisfelipejimenez.producer;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.producer.utils.MessageGenerator;
import com.luisfelipejimenez.producer.vo.EyeTrackingGenMessage;
import com.luisfelipejimenez.transportmanager.ITransportManager;
import com.luisfelipejimenez.transportmanager.TransportManager;

public class Producer 
{
	private static Logger logger = LogManager.getLogger(Producer.class);
	public static ITransportManager transportManager = null;
	private Properties properties = null;
	private static long delay;
	private static long period;
	
	public static EyeTrackingGenMessage messageGen()
	{
		EyeTrackingGenMessage msg = null;

		try 
		{
			Timer timer = new Timer();
			timer.schedule(new MessageGenerator(), delay, period);
			
		} catch (Exception e) {
			logger.error("An exception occurred while generating a message",e);
		}
		return msg;
	}

	public static void main(String[] args) 
	{
		String transportPropertyfile = null;
		String propertyfile = null;
		
		try 
		{
			if(args != null && args.length > 0)
			{

				transportPropertyfile = args[0];
				propertyfile = args[1];
				logger.info("propertyfile ->: "+propertyfile);
				
				logger.debug("Value:" + System.getProperty("log4j.configurationFile"));
				logger.debug("Value:" + System.getProperty("logfile.name"));

				if(propertyfile != null)
				{
					transportManager = new TransportManager(transportPropertyfile);
					Producer producer = new Producer(); 
					producer.initProperties(propertyfile);
					producer.initService();
					messageGen();		
				}

			}
			else
				System.out.println("propertyfile not found");
			
			
			System.out.println("Exiting main");
			
		} catch (Exception e) {
			logger.error("An exception occurred in main: ",e);
		}
		
	}
	
	public void initService()
	{
		logger.info("initService");
		transportManager.initSenderConfig();
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
				
				if(properties.getProperty("delay") != null && !properties.getProperty("delay").isEmpty())
					delay = Long.valueOf(properties.getProperty("delay"));
				else
					logger.info("initProperties() - delay not found");
				
				if(properties.getProperty("period") != null && !properties.getProperty("period").isEmpty())
					period = Long.valueOf(properties.getProperty("period"));
				else
					logger.info("initProperties() - portNumber not found");
			
			}
			else 
			{
				logger.info("initSenderMq() - propFileName null or empty string" );
			}
			
			
		} catch (Exception e) {
			logger.error("initProperties() - Not valid queueName",e);
		}
		
		logger.info("initSenderMq() - Ends" );
		
	}

}
