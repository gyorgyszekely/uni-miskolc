package hu.miskolc.uni.iit.dist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.miskolc.uni.iit.dist.domain.CustomerPassword;
import hu.miskolc.uni.iit.dist.domain.MessageDescriptorRequest;
import hu.miskolc.uni.iit.dist.domain.MessageType;
import hu.miskolc.uni.iit.dist.domain.SendMessageReply;
import hu.miskolc.uni.iit.dist.domain.SendMessageRequest;
import hu.miskolc.uni.iit.dist.service.SendEmailService;
import hu.miskolc.uni.iit.dist.service.UserDetailsProvider;

@Controller
public class CustomerMessageController
{

	@Autowired
	private SendEmailService service;

	@Autowired
	private UserDetailsProvider userService;

	@RequestMapping(value = "/restoreemailrequest")
	@ResponseBody
	public String restoreEmailRequestHandler(@ModelAttribute() CustomerPassword password)
	{
		MessageDescriptorRequest details = new MessageDescriptorRequest();
		details.setRecipient(password.getCustomerId());
		details.setMessageText("Have a nice day!");
		details.setMessageType(MessageType.EMAIL);
		
		
		SendMessageReply result = service.sendEmail(details);
		return result.getMessageText();
	}

	@RequestMapping(value = "/sendmessage")
	@ResponseBody
	public boolean sendMessageHandler(@RequestBody SendMessageRequest message)
	{
		
		MessageDescriptorRequest details = new MessageDescriptorRequest();
		details.setRecipient(message.getCustomerId());
		details.setMessageText(message.getMessageText());
		details.setMessageType(MessageType.INTERNAL);
		
		SendMessageReply report = service.sendEmail(details);
		return report.isSuccess();
	}

	@RequestMapping(value = "/getmessages")
	@ResponseBody
	public List<String> getMessages(@RequestBody String userid)
	{
		return userService.getMessages(userid);
	}

}
