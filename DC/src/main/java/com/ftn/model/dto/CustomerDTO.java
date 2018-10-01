package com.ftn.model.dto;

import com.ftn.model.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Jasmina on 21/11/2017.
 */
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

    public CustomerDTO(Customer customer){
        this(customer, true);
    }
    
    public CustomerDTO(Customer customer, boolean cascade){
        super(customer);
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.personalId = customer.getPersonalId();
        this.passport = customer.getPassport();
        this.address = customer.getAddress();
        this.telephoneNumber = customer.getTelephoneNumber();
        this.carrier = customer.isCarrier();
        this.email = customer.getEmail();
    }



    public Customer construct(){
        final Customer customer = new Customer(this);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPersonalId(personalId);
        customer.setPassport(passport);
        customer.setAddress(address);
        customer.setTelephoneNumber(telephoneNumber);

        customer.setAddress(address);

        customer.setCarrier(carrier);

        customer.setEmail(email);
        return customer;
    }
}
