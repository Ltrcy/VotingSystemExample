package com.litrc.votingsystem.vote.payload;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserPasswordUpdateForm {
    @NotBlank
    @Size(max = 128)
    private String password;

    public String getPassword() {
        return password;
    }
}