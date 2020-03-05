package com.litrc.votingsystem.vote.payload;


import javax.validation.constraints.NotBlank;

public class CreateNewVoteForm {
    @NotBlank
    private Long choiceId;

    public Long getChoiceId() {
        return choiceId;
    }
}
