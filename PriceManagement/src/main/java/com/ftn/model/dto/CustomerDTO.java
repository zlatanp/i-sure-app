package com.ftn.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CustomerDTO extends BaseDTO{

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	@Size(min = 13, max = 13)
	@Pattern(regexp = "[0-9]*")
	private String personalId;

	@NotNull
	@Size(min = 9, max = 9)
	@Pattern(regexp = "[0-9]*")
	private String passport;

	@NotNull
	private String address;

	private String telephoneNumber;

	@NotNull
	private boolean carrier;

	private String email;


}
