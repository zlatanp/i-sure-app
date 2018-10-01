package com.ftn.model.authorisation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import com.ftn.model.Base;
import com.ftn.util.SqlConstants;

@Entity
@Table(name="permission")
@SQLDelete(sql = SqlConstants.UPDATE + "permission" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Permission extends Base{
	
	@Column(unique = true, nullable = false)
	@Size(max = 40)
	@NotEmpty
	private String name;

	
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permission(String name) {
		super();
		this.name = name;
	}

	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
