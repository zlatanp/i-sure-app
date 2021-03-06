package com.ftn.repository.authorisation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.authorisation.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
		public Role save(Role role);
		public Optional<Role> findById(Long id);
		
		public List<Role> findAll();
		public Optional<Role> findByName(String name);

}
