package hu.miskolc.uni.iit.dist.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import hu.miskolc.uni.iit.dist.domain.MessageDescriptorRequest;
import hu.miskolc.uni.iit.dist.domain.SendMessageReply;
import hu.miskolc.uni.iit.dist.service.UserDetailsProvider;

public class MessageActivator
{
	
		@Autowired
		private UserDetailsProvider service;
		
		public @ResponsePayload SendMessageReply activate(Message<MessageDescriptorRequest> message) {
			
			MessageDescriptorRequest email = message.getPayload();
			SendMessageReply messageReply = new SendMessageReply();
			if(email == null) 
			{
				messageReply.setSuccess(false);
				messageReply.setMessageText("User not found!");
				return messageReply;
			}
			service.sendMessage(email.getRecipient(), email.getMessageText());
			messageReply.setSuccess(true);
			messageReply.setMessageText("Success!");
			return messageReply;
		}
		

}
