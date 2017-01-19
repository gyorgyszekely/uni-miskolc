package hu.miskolc.uni.iit.dist.integration;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import hu.miskolc.uni.iit.dist.domain.CustomerDescriptorRequest;
import hu.miskolc.uni.iit.dist.domain.SendMessageReply;
import hu.miskolc.uni.iit.dist.service.UserDetailsProvider;

public class SendEmailActivator
{

	@Autowired
	private UserDetailsProvider service;

	@Autowired
	private JavaMailSender mailSender;

	public @ResponsePayload SendMessageReply handleEmailSend(Message<CustomerDescriptorRequest> message)
	{
		CustomerDescriptorRequest details = message.getPayload();
		SendMessageReply messageReply = new SendMessageReply();
		messageReply.setSuccess(false);

		if (details == null)
		{
			messageReply.setMessageText("Ivlaid Payload.");
			return messageReply;
		}

		String userid = details.getCustomerId();
		if (userid == null)
		{
			messageReply.setMessageText("Cannot find user!");
			return messageReply;
		}

		CustomerDescriptorRequest user = service.getUserDetails(userid);
		if (user == null)
		{
			messageReply.setMessageText("Invalid user data!");
			return messageReply;
		}
		String email = user.getEmailAdress();
		if (email == null)
		{
			messageReply.setMessageText("Invalid user email!");
			return messageReply;
		}
		try
		{
			MimeMessage Emessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(Emessage);
			helper.setTo(email);
			helper.setSubject("Dear " + userid + "!");
			helper.setText("Hi!<br/><br/> userid: " + userid + "<br/><br/> Message: " + details.getMessageText(), true);
			// mailSender.send(Emessage);
		} catch (Exception e)
		{
			messageReply.setMessageText("Email sending failed!");
			return messageReply;
		}
		messageReply.setSuccess(true);
		messageReply.setMessageText("Success!");
		return messageReply;
	}

}
