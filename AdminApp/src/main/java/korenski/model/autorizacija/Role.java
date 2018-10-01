package korenski.model.autorizacija;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import util.SqlConstants;

@Entity
@Table(name="role")
@SQLDelete(sql = SqlConstants.UPDATE + "role" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)

public class Role extends Base{

	@Column(unique = true, nullable = false)
	@Size(max = 30)
	@NotEmpty
	private String name;
	
	@Column(name="permissions")
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Permission> permissions;

	public Role() {
		super();
		permissions = new ArrayList<>();
		
	}
	
	

	public Role(String name, Collection<Permission> permissions) {
		super();
		
		this.name = name;
		this.permissions = permissions;
	}



	public Role(String name) {
		super();
		this.name = name;
		permissions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

	
	
	
}
