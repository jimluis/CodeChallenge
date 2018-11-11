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
		String outputFile = null;
		ITransportManager transportService = null;
	
		try 
		{	
				if(args != null && args.length > 0)
				{				
					propertyfile = args[0];
					outputFile = args[1];
					
					logger.info("propertyfile: "+propertyfile);
					logger.info("outputFile: "+outputFile);

					if(propertyfile != null)
					{
						transportService = new TransportManager(propertyfile);
						transportService.initReceiverConfig();
						transportService.setConsumer(new EventHandler(outputFile));
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
