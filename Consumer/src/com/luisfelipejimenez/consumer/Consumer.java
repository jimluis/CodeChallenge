package com.luisfelipejimenez.consumer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.transportmanager.TransportManager;


public class Consumer
{
	private static Logger logger = LogManager.getLogger(Consumer.class);
	
	public static void main(String[] args) throws Exception 
	{
		
		String propertyfile = null;
		
		try 
		{
			if(args != null && args.length > 0)
			{
				for (int i = 0; i < args.length; i++) {
					System.out.println("args: "+args[i]);
				}
				

				logger.debug("Value:" + System.getProperty("log4j.configurationFile"));
				logger.debug("Value:" + System.getProperty("logfile.name"));
				
				propertyfile = args[0];
				logger.info("propertyfile-: "+propertyfile);
				
				if(propertyfile != null)
				{
					TransportManager transportService = new TransportManager(propertyfile);
					transportService.initReceiverMq();
					transportService.setConsumer(new EventHandler(transportService.getChannel()));
					transportService.initConsumer();				
				}
					
			}
			else
				logger.info("propertyfile not found");

			
		} catch (Exception e) {
			logger.error("An exception occurred in main: ",e);
		}


	}

	
	
}
