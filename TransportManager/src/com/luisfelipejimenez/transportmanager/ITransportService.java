package com.luisfelipejimenez.transportmanager;

import com.luisfelipejimenez.vo.MessageVO;
import com.rabbitmq.client.Channel;

public interface ITransportService 
{
	public void sender(MessageVO msg);
	public void initReceiverMq();
	public void initSenderMq();
	public void receiver(MessageVO msg);
	public void processMessage(MessageVO msg);
	public void setReceiver(Receiver consumer);
	//public void getChannel();
}
