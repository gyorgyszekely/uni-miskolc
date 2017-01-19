package hu.miskolc.uni.iit.dist.integration;

import hu.miskolc.uni.iit.dist.domain.MessageDescriptorRequest;
import hu.miskolc.uni.iit.dist.domain.MessageType;

/**
 * This class is responsible for decide {@link MessageType}.
 * @author gyuri
 *
 */
public class MessageRouter
{
	public String routeDecision(MessageDescriptorRequest email)
	{
		if (MessageType.EMAIL.equals(email.getMessageType()))
		{
			return "sendEmailChannel";
		}
		return "sendMessageChannel";
	}
}
