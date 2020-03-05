package com.litrc.votingsystem.vote.controller;

import com.litrc.votingsystem.vote.exception.ResourceNotFoundException;
import com.litrc.votingsystem.vote.model.Choice;
import com.litrc.votingsystem.vote.model.Poll;
import com.litrc.votingsystem.vote.payload.CreateNewChoiceForm;
import com.litrc.votingsystem.vote.payload.UpdateChoiceForm;
import com.litrc.votingsystem.vote.service.ChoiceService;
import com.litrc.votingsystem.vote.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/polls/{pollId}/choices")
public class ChoiceController {
    private PollService pollService;
    private ChoiceService choiceService;

    @Autowired
    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }

    @Autowired
    public void setChoiceService(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping
    public List<Choice> findByPollId(@PathVariable Long pollId) {
        return choiceService.findByPollId(pollId);
    }

    // 创建选择权（form） choose A or B or C or D
    @PostMapping
    public Choice createNewChoice(@PathVariable Long pollId,
                                  @Valid @RequestBody CreateNewChoiceForm form) {
        return choiceService.create(pollId, form);
    }

    // 更改选项的内容
    @PutMapping("/{id}")
    public Choice updateChoice(@PathVariable Long pollId,
                               @PathVariable Long id,
                               @Valid @RequestBody UpdateChoiceForm form) {
        return choiceService.update(pollId, id, form);
    }

    // 删除自己的选择权
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChoice(@PathVariable Long pollId,
                                          @PathVariable Long id) {
        choiceService.delete(pollId, id);
        return ResponseEntity.ok().build();
    }
}
