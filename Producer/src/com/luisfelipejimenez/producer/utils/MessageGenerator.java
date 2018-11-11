package com.luisfelipejimenez.producer.utils;

import java.util.Random;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.producer.Producer;
import com.luisfelipejimenez.transportmanager.ITransportManager;
import com.luisfelipejimenez.vo.MessageVO;


public class MessageGenerator extends TimerTask 
{
	private static Logger logger = LogManager.getLogger(Producer.class);
	ITransportManager transportManager = null;
	
	@Override
	public void run() 
	{
		MessageVO message = new MessageVO();
		message = eyeTrackingGenMessage(message);

		transportManager = Producer.transportManager;
		transportManager.sender(message);
		logger.info("message sent");
		
	}
	
	public MessageVO eyeTrackingGenMessage (MessageVO message) 
	{
		Random random = new Random();
		
		message.setConfidence(random.nextFloat());
		message.setEyeId(random.nextBoolean());
		message.setNormalizedPosX(random.nextFloat());
		message.setNormalizedPosY(random.nextFloat());
		message.setPupilDiameter(random.nextFloat());
		message.setTimestamp(System.currentTimeMillis());
		
		return message;

	}
	

}
