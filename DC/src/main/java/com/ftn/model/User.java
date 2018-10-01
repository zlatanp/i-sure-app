package com.ftn.model;

import com.ftn.model.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Jasmina on 16/11/2017.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "isureUsers")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public void merge(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }
}
