package hu.miskolc.uni.iit.dist.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User object with specified constraints.
 * 
 * @author gyuri
 *
 */
public class User 
{
	private String userId;
	
	private String userName;
	
	private String creditBalance;
	
	private Qualification qualification;
	
	private Gender gender;
	
	private List<String> favouriteColor;
	
	
	public User()
	{
		this.userId = UUID.randomUUID().toString();
	}

	public String getUserId()
	{
		return userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public User setUserName(String userName)
	{
		this.userName = userName;
		return this;
	}

	public String getCreditBalance()
	{
		return creditBalance;
	}

	public User setCreditBalance(String creditBalance)
	{
		this.creditBalance = creditBalance;
		return this;
	}

	public Qualification getQualification()
	{
		return qualification;
	}

	public User setQualification(Qualification qualification)
	{
		this.qualification = qualification;
		return this;
	}

	public Gender getGender()
	{
		return gender;
	}

	public User setGender(Gender gender)
	{
		this.gender = gender;
		return this;
	}

	public List<String> getFavouriteColor()
	{
		if(favouriteColor == null)
		{
			favouriteColor = new ArrayList<>();
		}
		return favouriteColor;
	}

	public User setFavouriteColor(List<String> favouriteColor)
	{
		this.favouriteColor = favouriteColor;
		return this;
	}

}
