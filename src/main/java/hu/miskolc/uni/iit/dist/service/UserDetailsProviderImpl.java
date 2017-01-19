package hu.miskolc.uni.iit.dist.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.miskolc.uni.iit.dist.domain.CustomerDescriptorRequest;

public class UserDetailsProviderImpl implements UserDetailsProvider
{

	private Map<String, CustomerDescriptorRequest> userMap = new HashMap<>();
	
	@Override
	public CustomerDescriptorRequest getUserDetails(String userid)
	{
		return userMap.get(userid);
	}

	@Override
	public Collection<CustomerDescriptorRequest> getUsers()
	{
		return userMap.values();
	}

	@Override
	public boolean sendMessage(String userid, String message)
	{
		CustomerDescriptorRequest cust = userMap.get(userid);
		if(cust == null)
		{
			return false;
		}
		cust.getMessageText().add(message);
		return true;
	}

	@Override
	public List<String> getMessages(String userid)
	{
		CustomerDescriptorRequest cust = userMap.get(userid);
		if(cust == null)
		{
			throw new IllegalArgumentException("Invalid Userid!");
		}
		return cust.getMessageText();
	}

}
