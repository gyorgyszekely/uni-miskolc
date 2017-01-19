package hu.miskolc.uni.iit.dist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value=RestController.BASE_URL)
public class CustomerSecurityController
{
	
	protected static final String BASE_URL = "/admin/";
	private static final String SECURELOGIN = "securelogin";

	@RequestMapping(value = SECURELOGIN)
	public ModelAndView loginproc()
	{
		return new ModelAndView("securelogin");
	}

}
