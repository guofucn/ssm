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

	public void test() {
		System.out.println("hello");
		User user = userService.getUserById(1);
		System.out.println(user.getPassword());
	}

	@RequestMapping("/hello")
	public ModelAndView hello() {
		//User user = userService.getUserById(1);

		ModelAndView mv = new ModelAndView();
		//mv.addObject("spring", "spring mvc" + user.getPassword());
		mv.addObject("spring", "spring在 mvc");
		mv.setViewName("hello");
		return mv;
	}
}
