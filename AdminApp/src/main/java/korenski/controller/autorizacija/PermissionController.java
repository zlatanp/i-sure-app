package korenski.controller.autorizacija;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import korenski.exception.BadRequestException;
import korenski.model.autorizacija.Permission;
import korenski.service.PermissionService;
import korenski.service.RoleService;


@Controller
public class PermissionController {

	
	PermissionService permissionService;
	
	@Autowired
    public PermissionController(PermissionService permissionService){
        this.permissionService = permissionService;
    }
	
	//@PreAuthorize("authenticated")
	//@CustomAnnotation(value = "INSERT_PERMISSION")
	@PostMapping(value = "/newPermission")
	public ResponseEntity newPermission(@Valid @RequestBody Permission permission, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors())
			throw new BadRequestException();
		
		Permission perm;
		try {
			perm = permissionService.create(permission);
			return new ResponseEntity<>(perm, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
		
	}
	
	//@CustomAnnotation(value = "DELETE_PERMISSION")
	@DeleteMapping(value = "/deletePermission/{id}")
	public ResponseEntity<Permission> deletePermission(@PathVariable("id") Long id) throws Exception {

		try {
			permissionService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	//@CustomAnnotation(value = "UPDATE_PERMISSION")
	@PatchMapping(value = "/updatePermission/{id}")
	public ResponseEntity<Permission> updatePermission(@PathVariable Long id, @Valid @RequestBody Permission permission, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BadRequestException();
		}

		
		Permission permissionModified = null;
		
		try {
			permissionModified = permissionService.update(id, permission);
		} catch (Exception e) {
			
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(permissionModified, HttpStatus.OK);
	}
	
	//@CustomAnnotation(value = "FIND_ALL_PERMISSION")
	@GetMapping(value = "/allPermissions")
	public ResponseEntity<Collection<Permission>> allPermissions() throws Exception {

		return new ResponseEntity<>(permissionService.readAll(), HttpStatus.OK);
		
	}
	
	
	
}
