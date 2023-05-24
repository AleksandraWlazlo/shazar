package com.fdmgroup.Group4ProjectShazar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Role;
import com.fdmgroup.Group4ProjectShazar.repository.RoleRepository;

@Service
public class RoleService {
	
	private RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Role findByRoleName(String roleName) {
		Optional<Role> optionalRole = roleRepository.findByRoleName(roleName);
		
		return optionalRole.orElse(new Role("default role")); 
	}

}
