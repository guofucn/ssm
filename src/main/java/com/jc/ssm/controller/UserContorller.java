package com.jc.ssm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserContorller {

	@ResponseBody
	@RequestMapping("user/login")
	public Map<String, Object> login(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password") String password) {
		
		System.out.println(userName + "-" + password);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "b");
		map.put("date", new Date());
		return map;
	}
}
