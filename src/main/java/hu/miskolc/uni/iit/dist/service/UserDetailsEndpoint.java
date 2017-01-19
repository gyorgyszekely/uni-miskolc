package hu.miskolc.uni.iit.dist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import hu.miskolc.uni.iit.dist.domain.CustomerDescriptorRequest;

@Endpoint
public class UserDetailsEndpoint
{
		@Autowired
		private UserDetailsProvider service;
	
		public @ResponsePayload CustomerDescriptorRequest getUser(String userid) {
			return service.getUserDetails(userid);
		}

}
