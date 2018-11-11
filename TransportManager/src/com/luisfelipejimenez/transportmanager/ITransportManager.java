package com.luisfelipejimenez.transportmanager;

import com.luisfelipejimenez.vo.MessageVO;
import com.rabbitmq.client.Channel;

public interface ITransportManager 
{
	public void sender(MessageVO msg);	
	public void initReceiverConfig();
	public void initSenderConfig();
	public void setConsumer(Receiver consumer);
	public void initConsumer();
}
