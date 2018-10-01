package com.ftn.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Created by zlatan on 11/25/17.
 */
@Data
@NoArgsConstructor
public class User {

    private String name;
    private int number;

    public User(String name, int number){
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "User: { name=\"" + name + "\"" + ", number=" + number + " }";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }
}