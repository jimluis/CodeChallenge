package com.luisfelipejimenez.transportmanager;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.vo.MessageVO;


public class Send extends TransportManager
{
	private static Logger logger = LogManager.getLogger(Send.class);
	
	  public static void sender(MessageVO msg, TransportManager transp) 
	  {
		  
			logger.debug("sender() - Starts" );
			
			try 
			{
			  	byte[] data = SerializationUtils.serialize(msg);
			  	transp.getChannel().basicPublish("", transp.getQueueName(), null, data);
			    logger.info("sender() - Message sent: "+ msg.toString());
				    
			} catch (Exception e) {
				logger.error("sender() - An exception occurred while sending a message", e );
				
			}
			
			logger.debug("sender() - Ends" );
		    
		  }
}
