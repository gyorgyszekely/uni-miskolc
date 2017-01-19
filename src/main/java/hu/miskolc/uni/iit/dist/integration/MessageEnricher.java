package hu.miskolc.uni.iit.dist.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import hu.miskolc.uni.iit.dist.domain.MessageDescriptorRequest;

public class MessageEnricher
{

	public Message<MessageDescriptorRequest> enrichtEmailMessage(Message<MessageDescriptorRequest> message) {

		Map<String, Object> headers = new HashMap<>();
		headers.put("MSG_TYPE", message.getPayload().getMessageType());
		MessageHeaders header = new MessageHeaders(headers);
		Message<MessageDescriptorRequest> updatedMessage = MessageBuilder.createMessage(message.getPayload(), header);
		return updatedMessage;
	}
	
}
