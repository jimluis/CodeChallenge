package com.luisfelipejimenez.transportmanager;

import org.apache.commons.lang3.SerializationUtils;

import com.luisfelipejimenez.vo.MessageVO;


public class Send extends TransportService
{
	  public void sender(MessageVO msg) 
	  {
		  try 
		  {
			  	byte[] data = SerializationUtils.serialize(msg);
			    getChannel().basicPublish("", getQueueName(), null, data);
			    System.out.println(" [x] Sent '" + msg.toString() + "'");
//				TransportService.channel.close();
//			    TransportService.connection.close();
			    System.out.println(" Done");
			    
			} catch (Exception e) {
				 System.out.println(" [x] exception '" + e );
				
			}
//		  finally 
//		  	{

//			}
		    
		  }
}
