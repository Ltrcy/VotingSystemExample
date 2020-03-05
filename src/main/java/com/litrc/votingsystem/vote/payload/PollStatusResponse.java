package com.litrc.votingsystem.vote.payload;

public class PollStatusResponse {
    private Long choiceId;
    private Long choiceCount;

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }

    public void setChoiceCount(Long choiceCount) {
        this.choiceCount = choiceCount;
    }
}
