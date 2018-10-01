package com.ftn.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.authorisation.Role;
import com.ftn.repository.authorisation.RoleRepository;
import com.ftn.service.RoleService;
@Service
public class RoleServiseImplementation implements  RoleService{

	private final RoleRepository roleRepository;

    @Autowired
    public RoleServiseImplementation(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

	
	@Override
	public List<Role> readAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		return roleRepository.save(role);
	}

	@Override
	public Role update(Long id, Role role) {
		role.setName(role.getName().toUpperCase());
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name).orElseThrow(NotFoundException::new);
	}

}
