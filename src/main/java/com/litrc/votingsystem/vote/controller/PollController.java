package com.litrc.votingsystem.vote.controller;

import com.litrc.votingsystem.vote.exception.ResourceNotFoundException;
import com.litrc.votingsystem.vote.model.Choice;
import com.litrc.votingsystem.vote.model.Poll;
import com.litrc.votingsystem.vote.model.User;
import com.litrc.votingsystem.vote.payload.CreateNewPollForm;
import com.litrc.votingsystem.vote.payload.PollStatusResponse;
import com.litrc.votingsystem.vote.payload.UpdatePollForm;
import com.litrc.votingsystem.vote.service.ChoiceService;
import com.litrc.votingsystem.vote.service.PollService;
import com.litrc.votingsystem.vote.service.UserService;
import com.litrc.votingsystem.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {
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

    // 查看投票为id的问题
    @GetMapping("/{id}")
    public Poll findById(@PathVariable Long id) {
        return pollService.findById(id);
    }

    // 查看投票的问题
    @GetMapping
    public List<Poll> findAll() {
        return pollService.findAll();
    }

    // 查看票数
    @GetMapping("/{id}/status")
    public List<PollStatusResponse> status(@PathVariable Long id) {
        return pollService.responseList(id);
    }

    // 输入用户名和要让大家投票的问题
    @PostMapping
    public Poll createNewPoll(@RequestParam String username,
                              @Valid @RequestBody CreateNewPollForm form) {
        return pollService.create(username, form);
    }

    // 更新提出的问题
    @PutMapping("/{id}")
    public Poll updatePoll(@PathVariable Long id,
                           @Valid @RequestBody UpdatePollForm form) {
        return pollService.update(id, form);
    }

    // 删除提出的问题 需要先删除choice（选项）和vote（投的票）表的数据再删除poll（问题）
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePoll(@PathVariable Long id) {
        pollService.delete(id);
        return ResponseEntity.ok().build();
    }
}
