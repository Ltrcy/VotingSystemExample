package com.litrc.votingsystem.vote.controller;

import com.litrc.votingsystem.vote.model.Vote;
import com.litrc.votingsystem.vote.payload.CreateNewVoteForm;
import com.litrc.votingsystem.vote.service.ChoiceService;
import com.litrc.votingsystem.vote.service.PollService;
import com.litrc.votingsystem.vote.service.UserService;
import com.litrc.votingsystem.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author litrc
 * @date 2020.02.28
 */

@RestController
@RequestMapping("/polls/{pollId}/votes")
public class VoteController {
    @Autowired
    private UserService userService;
    @Autowired
    private PollService pollService;
    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private VoteService voteService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }

    @Autowired
    public void setChoiceService(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    // 实现投票 如：投A选票 或者 投B选票
    @PostMapping
    public Vote createNewVote(@PathVariable Long pollId,
                              @RequestParam String username,
                              @RequestBody CreateNewVoteForm form) {
        return voteService.create(pollId, username, form);
    }
}