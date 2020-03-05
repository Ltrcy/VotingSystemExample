package com.litrc.votingsystem.vote.payload;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserEmailUpdateForm {
    @NotBlank
    @Size(max = 64)
    @Email
    private String email;

    public String getEmail() {
        return email;
    }
}
