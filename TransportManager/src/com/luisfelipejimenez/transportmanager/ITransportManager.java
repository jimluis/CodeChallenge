package com.luisfelipejimenez.transportmanager;

import com.luisfelipejimenez.vo.MessageVO;
import com.rabbitmq.client.Channel;

public interface ITransportManager 
{
	public void sender(MessageVO msg);	
	public void initReceiverMq();
	public void initSenderMq();
}
