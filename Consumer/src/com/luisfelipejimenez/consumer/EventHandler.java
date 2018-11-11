package com.luisfelipejimenez.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.transportmanager.Receiver;
import com.luisfelipejimenez.vo.MessageVO;

public class EventHandler extends Receiver
{
	private static Logger loggerMessage = LogManager.getLogger(EventHandler.class);
	
	@Override
	public void processMessage(MessageVO msg) 
	{
		if(msg != null)
			loggerMessage.info(" processMessage() - message received: "+msg.toString() );
		else
			loggerMessage.info(" message null ");
	}



}
