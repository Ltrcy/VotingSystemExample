package com.litrc.votingsystem.vote.payload;


import javax.validation.constraints.NotBlank;

public class UpdateChoiceForm {
    @NotBlank
    private String text;


    public String getText() {
        return text;
    }
}