package hu.miskolc.uni.iit.dist.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.miskolc.uni.iit.dist.dao.UserDao;
import hu.miskolc.uni.iit.dist.domain.FavoriteColorType;
import hu.miskolc.uni.iit.dist.domain.QualificationType;
import hu.miskolc.uni.iit.dist.domain.SimpleRestReply;
import hu.miskolc.uni.iit.dist.domain.User;
import hu.miskolc.uni.iit.dist.domain.UserPreferencePreloadReply;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

@Controller
@RequestMapping(value=RestController.BASE_URL)
public class RestController
{
	protected static final String BASE_URL = "/adminapi/";
	private static final String PRELOAD = BASE_URL + "preload";
	private static final String MANAGE = BASE_URL + "manage";
	private static final String VIEW = BASE_URL + "view";
	private static final String DELETE = BASE_URL + "delete";
	
	@Autowired
	private UserDao userDao;
	
	
	@PostMapping(value = PRELOAD)
	@ResponseBody
	public UserPreferencePreloadReply preload()
	{
		UserPreferencePreloadReply reply = new UserPreferencePreloadReply();
		reply.getFavoriteColorType().add(new FavoriteColorType("red", "red", false));
		reply.getFavoriteColorType().add(new FavoriteColorType("green", "green", false));
		reply.getFavoriteColorType().add(new FavoriteColorType("blue", "blue", false));
		reply.getQualificationType().add(new QualificationType("HIGHSCHOOL", "High School"));
		reply.getQualificationType().add(new QualificationType("COLLEGE", "College"));
		reply.getQualificationType().add(new QualificationType("UNIVERSITY", "University"));
		return reply;
	}
	
	
	@PostMapping(value = MANAGE, consumes = "application/json")
	@ResponseBody
	public SimpleRestReply registerUser(@RequestBody @Valid User user, BindingResult result) {
		System.out.println(">>>>>>>>>>>>>>hu.miskolc.uni.iit.dist.controller.RestController.registerUser(): " + user.toString());
		SimpleRestReply reply = new SimpleRestReply();
		if (result.hasErrors()) {
			return reply.setSuccess(false);
		}
		userDao.storeUser(user);
		return reply.setSuccess(true);
	}

	@RequestMapping(value = VIEW)
	@ResponseBody
	public Collection<User> getUsers() {
		return userDao.getUsers();
	}

	@PostMapping(value = DELETE, consumes = "application/json")
	@ResponseBody
	public SimpleRestReply deleteUserHandler(@RequestBody String userId) {
		SimpleRestReply reply = new SimpleRestReply();
		try
		{
			userDao.deleteUser(userId);
		} catch (InvalidParameterException e)
		{
			return reply.setSuccess(false);
		}
		return reply.setSuccess(true);
	}
	
}
