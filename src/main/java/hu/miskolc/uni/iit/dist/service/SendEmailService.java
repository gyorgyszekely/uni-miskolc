package hu.miskolc.uni.iit.dist.service;

import java.util.Map;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import hu.miskolc.uni.iit.dist.domain.CustomerDescriptorRequest;
import hu.miskolc.uni.iit.dist.domain.MessageDescriptorRequest;
import hu.miskolc.uni.iit.dist.domain.SendMessageReply;

public interface SendEmailService
{
	SendMessageReply sendEmail(@RequestPayload MessageDescriptorRequest email, @Headers Map<String, String> headers);
	
	SendMessageReply sendEmail(MessageDescriptorRequest email);
}
