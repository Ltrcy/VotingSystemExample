package com.litrc.votingsystem.vote.payload;


import javax.validation.constraints.NotBlank;

public class UpdatePollForm {
    @NotBlank
    private String question;

    public String getQuestion() {
        return question;
    }
}
