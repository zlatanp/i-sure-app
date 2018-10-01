package korenski.repository.autorizacija;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import korenski.model.autorizacija.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	public Permission save(Permission Permission);
	public Optional<Permission> findById(Long id);
	
	public List<Permission> findAll();
	public Optional<Permission> findByName(String name);

}