package com.ftn.service;

import java.util.List;

import com.ftn.model.authorisation.Permission;

public interface PermissionService {

	List<Permission> readAll();

	Permission create(Permission permission);

	Permission update(Long id, Permission permission);

	void delete(Long id);

	Permission findById(Long id);

	Permission findByName(String name);

}
