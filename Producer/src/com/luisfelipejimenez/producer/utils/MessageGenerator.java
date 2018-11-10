package com.luisfelipejimenez.producer.utils;

import java.util.Random;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luisfelipejimenez.mindx.transportservice.ITransportService;
import com.luisfelipejimenez.producer.Producer;
import com.luisfelipejimenez.vo.MessageVO;


public class MessageGenerator extends TimerTask 
{
	private static Logger logger = LogManager.getLogger(Producer.class);
	ITransportService transportService = null;
	@Override
	public void run() 
	{
		MessageVO message = new MessageVO();
		message = eyeTrackingGenMessage(message);

		transportService = Producer.transportService;
		transportService.sender(message);
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
