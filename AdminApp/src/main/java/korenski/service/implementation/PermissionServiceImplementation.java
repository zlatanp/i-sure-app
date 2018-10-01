package korenski.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import korenski.model.autorizacija.Permission;
import korenski.service.PermissionService;

@Service
public class PermissionServiceImplementation implements PermissionService{

	@Value("${dc.permissions}")
	String uri;
	
	@Override
	public List<Permission> readAll() {
		RestTemplate restTemplate = new RestTemplate();
		String specific_uri = uri+"/allPermissions";
		ResponseEntity<Permission[]> response = restTemplate.getForEntity(specific_uri, Permission[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public Permission create(Permission permission) {
		RestTemplate restTemplate = new RestTemplate();
		String specific_uri = uri+"/newPermission";
		ResponseEntity<Permission> response = restTemplate.postForEntity(specific_uri, new HttpEntity<>(permission),
				Permission.class);

		return response.getBody();
	}

	@Override
	public Permission update(Long id, Permission permission) {
		RestTemplate restTemplate = new RestTemplate();
		String specific_uri = uri+"/updatePermission/"+id;
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		restTemplate.setRequestFactory(requestFactory);

		ResponseEntity<Permission> response = restTemplate.exchange(specific_uri, HttpMethod.PATCH,
				new HttpEntity<>(permission), Permission.class);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		String specific_uri = uri+"/deletePermission/"+id;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(specific_uri);
	}

	@Override
	public Permission findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

//	private final PermissionRepository permissionRepository;
//
//    @Autowired
//    public PermissionServiceImplementation(PermissionRepository permissionRepository){
//        this.permissionRepository = permissionRepository;
//    }
//
//	
//	@Override
//	public List<Permission> readAll() {
//		return permissionRepository.findAll();
//	}
//
//	@Override
//	public Permission create(Permission permission) {
//		
//		return permissionRepository.save(permission);
//	}
//
//	@Override
//	public Permission update(Long id, Permission permission) {
//		
//		Permission permissionModified = permissionRepository.findById(id).orElseThrow(NotFoundException::new);
//		
//		permissionModified.setName(permission.getName());
//		
//		return permissionRepository.save(permission);
//	}
//
//	@Override
//	public void delete(Long id) {
//		permissionRepository.delete(id);
//	}
//
//	@Override
//	public Permission findById(Long id) {
//		return permissionRepository.findById(id).orElseThrow(NotFoundException::new);
//	}
//
//	@Override
//	public Permission findByName(String name) {
//		return permissionRepository.findByName(name).orElseThrow(NotFoundException::new);
//	}
	
	

}
