package com.luisfelipejimenez.consumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.transportmanager.Receiver;
import com.luisfelipejimenez.vo.MessageVO;

public class EventHandler extends Receiver
{
	
	OutputStream os = null;

	public EventHandler(String outputFile) {
		super();
		
		 try {
			os = new FileOutputStream(new File(outputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
				isMessageProcessed = writeToFile(msg.toString());
				
			}
			else
				logger.info(" message null ");
			
		} catch (Exception e) {
			logger.error("processMessage() - An exception occurred while processing message ", e);
			isMessageProcessed = false;
		}
		return isMessageProcessed;

	}
	
	
	private boolean writeToFile(String data) 
	{
		boolean isMsgWritten = false;
		
        try 
        {
            os.write(data.getBytes(), 0, data.length());
            
            isMsgWritten = true;
            
        } catch (IOException e) {
        	isMsgWritten = false;
            e.printStackTrace();
        }
        
        return isMsgWritten;
    }
	

}
