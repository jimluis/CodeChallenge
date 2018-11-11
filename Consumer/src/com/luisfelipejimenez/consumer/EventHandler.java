package com.luisfelipejimenez.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.transportmanager.Receiver;
import com.luisfelipejimenez.vo.MessageVO;

public class EventHandler extends Receiver
{
	
	public EventHandler() {
		super();
	}

	private static Logger logger = LogManager.getLogger(EventHandler.class);
	

	@Override
	public boolean processMessage(MessageVO msg) 
	{
		boolean isMessageProcessed = false;
		try 
		{
			if(msg != null) 
			{
				isMessageProcessed = true;
				logger.info(" processMessage() - message received: "+msg.toString() );
			}
			else
				logger.info(" message null ");
			
		} catch (Exception e) {
			logger.error("processMessage() - An exception occurred while processing message ", e);
			isMessageProcessed = false;
		}
		return isMessageProcessed;

	}



}
