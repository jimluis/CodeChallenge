package com.luisfelipejimenez.consumer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.transportmanager.ITransportManager;
import com.luisfelipejimenez.transportmanager.TransportManager;


public class Consumer
{
	private static Logger logger = LogManager.getLogger(Consumer.class);
	
	public static void main(String[] args) throws Exception 
	{
		
		String propertyfile = null;
		ITransportManager transportService = null;
		
		try 
		{
			if(args != null && args.length > 0)
			{				
				propertyfile = args[0];
				
				logger.info("propertyfile: "+propertyfile);
				
				if(propertyfile != null)
				{
					transportService = new TransportManager(propertyfile);
					transportService.initReceiverConfig();
					transportService.setConsumer(new EventHandler());
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
