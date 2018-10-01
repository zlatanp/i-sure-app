package com.ftn.model.dto;

import com.ftn.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Jasmina on 16/11/2017.
 */
@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User construct(){
        final User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
