package com.luisfelipejimenez.producer.vo;

import java.sql.Timestamp;
import java.util.Random;

import com.luisfelipejimenez.vo.MessageVO;

public class EyeTrackingGenMessage 
{
	Random random = new Random();

	public void EyeTrackingGenMessage (MessageVO message) 
	{
		message.setConfidence(random.nextFloat());
		message.setEyeId(random.nextBoolean());
		message.setNormalizedPosX(random.nextFloat());
		message.setNormalizedPosY(random.nextFloat());
		message.setPupilDiameter(random.nextFloat());
		message.setTimestamp(Timestamp.valueOf("2013-01-01 00:00:00").getTime());

	}
	
}
