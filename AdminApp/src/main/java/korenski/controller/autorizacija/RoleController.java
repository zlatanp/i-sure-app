package korenski.controller.autorizacija;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import korenski.exception.BadRequestException;
import korenski.model.autorizacija.Role;
import korenski.service.RoleService;

@Controller
public class RoleController {

	private final RoleService roleService;
	private Logger logger;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
        logger = LoggerFactory.getLogger(RoleController.class);
    }


	//@CustomAnnotation(value = "INSERT_ROLE")
	@PostMapping(value = "/newRole")
	public ResponseEntity<Role> newRole(@Valid @RequestBody Role role , BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors())
			throw new BadRequestException();
		logger.info("Role create");
		return new ResponseEntity<>(roleService.create(role), HttpStatus.OK);
		
	}
	
	//@CustomAnnotation(value = "DELETE_ROLE")
	@DeleteMapping(value = "/deleteRole/{id}") //String id_string
	public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id ) throws Exception {
		
		try {
			roleService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	//@CustomAnnotation(value = "UPDATE_ROLE")
	@PatchMapping(value = "/updateRole/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable Long id, @Valid @RequestBody Role role ,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BadRequestException();
		}

		Role roleToModify = null;
		
		try {
			roleToModify = roleService.update(id, role);
			logger.info("Role update");
		} catch (Exception e) {
			logger.error("Error:role create");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(roleToModify, HttpStatus.OK);
	}
	
	//@CustomAnnotation(value = "FIND_ALL_ROLE")
	@RequestMapping(
			value = "/allRoles",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Role>> allRoles() throws Exception {

		return new ResponseEntity<>(roleService.readAll(), HttpStatus.OK);
		
	}
	
	
}
