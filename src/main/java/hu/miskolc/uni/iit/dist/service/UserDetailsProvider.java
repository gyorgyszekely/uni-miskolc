package hu.miskolc.uni.iit.dist.service;

import java.util.Collection;
import java.util.List;

import hu.miskolc.uni.iit.dist.domain.CustomerDescriptorRequest;

public interface UserDetailsProvider
{
	CustomerDescriptorRequest getUserDetails(String userid);

	Collection<CustomerDescriptorRequest> getUsers();

	boolean sendMessage(String userid, String message);

	List<String> getMessages(String userid);

}
