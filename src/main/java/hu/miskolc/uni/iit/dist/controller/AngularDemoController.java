package hu.miskolc.uni.iit.dist.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AngularDemoController
{

	@RequestMapping(value = "/getFruits", produces = "application/json")
	@ResponseBody
	public List<String> preload() {
		return Arrays.asList(new String[] { "cherry", "apple", "banana", "strawberry" });
	}
	
}
