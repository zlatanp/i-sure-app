package com.ftn.model.dto;

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
}
