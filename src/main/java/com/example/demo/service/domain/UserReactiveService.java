package com.example.demo.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.domainEntity.User;
import com.example.demo.repository.UserRepo;

import reactor.core.publisher.Mono;


public class UserReactiveService implements ReactiveUserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		
		 User user = userRepo.findByUsername(username);
		 
		 return Mono.just((UserDetails) user );
	}

}
