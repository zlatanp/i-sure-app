package com.ftn.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.authorisation.Permission;
import com.ftn.repository.authorisation.PermissionRepository;
import com.ftn.service.PermissionService;

@Service
public class PermissionServiceImplementation implements PermissionService{

	private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImplementation(PermissionRepository permissionRepository){
        this.permissionRepository = permissionRepository;
    }

	
	@Override
	public List<Permission> readAll() {
		return permissionRepository.findAll();
	}

	@Override
	public Permission create(Permission permission) {
		permission.setName(permission.getName().toUpperCase());
		return permissionRepository.save(permission);
	}

	@Override
	public Permission update(Long id, Permission permission) {
		
		Permission permissionModified = permissionRepository.findById(id).orElseThrow(NotFoundException::new);
		
		permissionModified.setName(permission.getName().toUpperCase());
		
		return permissionRepository.save(permissionModified);
	}

	@Override
	public void delete(Long id) {
		permissionRepository.delete(id);
	}

	@Override
	public Permission findById(Long id) {
		return permissionRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Permission findByName(String name) {
		return permissionRepository.findByName(name).orElseThrow(NotFoundException::new);
	}

}
