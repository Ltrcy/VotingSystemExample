package com.litrc.votingsystem.vote.payload;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotBlank
    @Size(max = 16)
    private String username;

    @NotBlank
    @Size(max = 128)
    private String password;

}