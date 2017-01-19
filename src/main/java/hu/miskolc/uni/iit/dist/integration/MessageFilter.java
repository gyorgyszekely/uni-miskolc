package hu.miskolc.uni.iit.dist.integration;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.util.StringUtils;

import hu.miskolc.uni.iit.dist.domain.MessageDescriptorRequest;

public class MessageFilter implements MessageSelector
{

	@Override
	public boolean accept(Message<?> message)
	{
		if(message instanceof MessageDescriptorRequest && !StringUtils.isEmpty( ((MessageDescriptorRequest) message.getPayload()).getMessageText()) )
		{
			return true;
		}
		return false;
	}
	
	

}
