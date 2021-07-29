package com.example.demo.controller.domain;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.demo.domainEntity.User;
import com.example.demo.service.domain.UserService;

@Controller
@RequestMapping("/userHome")
public class UserHome {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public EntityResponse<?> getUser(Principal principal, Authentication authentication,@AuthenticationPrincipal User user){
		
		//Less security
		User user1 = (User) userService.findAllByUsernameStartingWith(principal.getName());
		
		User user2  = (User) authentication.getPrincipal();
		
		//More clear code
		user.toString();
		
		//Can be use more deep in logic besides controller
		Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
		User user3  = (User) authentication2.getPrincipal();
		
		return null;
	}
	
}
