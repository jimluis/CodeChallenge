package com.luisfelipejimenez.consumer;

import com.luisfelipejimenez.transportmanager.TransportService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.transportmanager.ITransportService;
import com.luisfelipejimenez.transportmanager.Receiver;
import com.luisfelipejimenez.vo.MessageVO;


public class Consumer
{
	public static ITransportService transportService = null;
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
				

				System.out.println("Value:" + System.getProperty("log4j.configurationFile"));
				System.out.println("Value:" + System.getProperty("logfile.name"));
				propertyfile = args[0];
				System.out.println("propertyfile-: "+propertyfile);
				
				if(propertyfile != null)
				{
					TransportService transportService = new TransportService(propertyfile);
					transportService.initReceiverMq();
					transportService.setReceiver(new EventHandler());
					transportService.initConsumer();

					
					System.out.println("In main");
				}
				else
					System.exit(1);
			}
			else
				System.out.println("propertyfile not found");

			
		} catch (Exception e) {
			System.out.println("An exception occurred in main: "+e);
		}


	}

	
	
}
