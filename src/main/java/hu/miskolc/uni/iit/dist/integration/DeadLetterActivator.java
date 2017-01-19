package hu.miskolc.uni.iit.dist.integration;

import org.springframework.messaging.Message;

import hu.miskolc.uni.iit.dist.domain.MessageDescriptorRequest;
import hu.miskolc.uni.iit.dist.domain.SendMessageReply;

public class DeadLetterActivator
{

	public SendMessageReply sendFilteredEmailReport(Message<MessageDescriptorRequest> message)
	{
		SendMessageReply messageReply = new SendMessageReply();
		messageReply.setSuccess(false);
		messageReply.setMessageText("Invalid message format!");
		return messageReply;
	}

}
