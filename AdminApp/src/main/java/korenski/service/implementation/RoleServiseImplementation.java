package korenski.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import korenski.exception.NotFoundException;
import korenski.model.autorizacija.Permission;
import korenski.model.autorizacija.Role;
import korenski.repository.autorizacija.RoleRepository;
import korenski.service.RoleService;
@Service
public class RoleServiseImplementation implements  RoleService{

	@Value("${dc.roles}")
	String uri;
	
	@Override
	public List<Role> readAll() {
		RestTemplate restTemplate = new RestTemplate();
		String specific_uri = uri+"/allRoles";
		ResponseEntity<Role[]> response = restTemplate.getForEntity(specific_uri, Role[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public Role create(Role role) {
		RestTemplate restTemplate = new RestTemplate();
		String specific_uri = uri+"/newRole";
		ResponseEntity<Role> response = restTemplate.postForEntity(specific_uri, new HttpEntity<>(role),
				Role.class);

		return response.getBody();
	}

	@Override
	public Role update(Long id, Role role) {
		RestTemplate restTemplate = new RestTemplate();
		String specific_uri = uri+"/updateRole/"+id;
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		restTemplate.setRequestFactory(requestFactory);

		ResponseEntity<Role> response = restTemplate.exchange(specific_uri, HttpMethod.PATCH,
				new HttpEntity<>(role), Role.class);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		String specific_uri = uri+"/deleteRole/"+id;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(specific_uri);
	}

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
//	private final RoleRepository roleRepository;
//
//    @Autowired
//    public RoleServiseImplementation(RoleRepository roleRepository){
//        this.roleRepository = roleRepository;
//    }
//
//	
//	@Override
//	public List<Role> readAll() {
//		return roleRepository.findAll();
//	}
//
//	@Override
//	public Role create(Role role) {
//		
//		return roleRepository.save(role);
//	}
//
//	@Override
//	public Role update(Long id, Role role) {
//		return roleRepository.save(role);
//	}
//
//	@Override
//	public void delete(Long id) {
//		roleRepository.delete(id);
//	}
//
//	@Override
//	public Role findById(Long id) {
//		return roleRepository.findById(id).orElseThrow(NotFoundException::new);
//	}
//
//	@Override
//	public Role findByName(String name) {
//		return roleRepository.findByName(name).orElseThrow(NotFoundException::new);
//	}

}
