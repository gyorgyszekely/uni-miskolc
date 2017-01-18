package hu.miskolc.uni.iit.dist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value=MAVController.BASE_URL)
public class MAVController
{
	protected static final String BASE_URL = "";

	@RequestMapping(value = "/")
	@ResponseBody
	public String preload() {
		return "Spring is cool!";
	}

}
