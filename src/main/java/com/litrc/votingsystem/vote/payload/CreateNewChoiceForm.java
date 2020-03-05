package com.litrc.votingsystem.vote.payload;


import javax.validation.constraints.NotBlank;

public class CreateNewChoiceForm {
    @NotBlank
    private String text;

    public String getText() {
        return text;
    }
}
