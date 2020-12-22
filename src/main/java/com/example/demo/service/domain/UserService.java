package com.example.demo.service.domain;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domainEntity.Role;
import com.example.demo.domainEntity.User;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;



@Service
public class UserService implements UserDetailsService {

	
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private ModelMapper modelMapper;

	
	@Autowired
	public UserService(UserRepo userRepository, RoleRepo roleRepository, ModelMapper modelMapper) {
		super();
		this.userRepo=userRepository;
		this.roleRepo = roleRepository;
		this.modelMapper = modelMapper;
	}


	public User save(User user) {
		return userRepo.save(user);
	}


	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails userDetails= userRepo.findByUsername(username);
		
		
		 if (userDetails == null) {
	            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
	     }
		 
		 userDetails.getAuthorities().size();
		
		return userDetails;
	}


	public List<User> findAll() {
		
		return userRepo.findAll();
	}


	public User findById(Long id) {
		
		Optional<User> optional = userRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
		
		
	}
	
	public List<User> findAllByUsernameStartingWith(String startWith){
		
		return userRepo.findAllByUsernameStartingWith(startWith);
		
	}
	
public User findAllByUsername(String username){
		
		return userRepo.findByUsername(username);
		
	}
	
	
	public boolean register(UserRegisterDTO userRegisterDTO) {
		if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
			
			throw new IllegalStateException("Password and confirm password are different");
			
		}
		
		Role roleUser = roleRepo.findByName("ROLE_USER");
		User user = modelMapper.map(userRegisterDTO, User.class);
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.addRole(roleUser);
		
		
		
		if (userRepo.save(user)!=null) {
			
			return true;
			
			
		}
		
		throw new IllegalStateException("Can not register user");
		
		
		
		
	}
	
	
	
	
	
}
