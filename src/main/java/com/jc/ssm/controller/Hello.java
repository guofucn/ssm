package com.jc.ssm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jc.ssm.model.User;
import com.jc.ssm.service.UserService;

@Controller
public class Hello {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		User user = userService.getUserById(1);
		System.out.println(user.getPassword());
		mv.addObject("user", user.getUserName() + " " + user.getPassword());
		mv.setViewName("test");
		return mv;
	}

	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("spring", "spring在 mvc");
		mv.setViewName("hello");
		return mv;
	}
}
