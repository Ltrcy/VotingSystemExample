package com.litrc.votingsystem.vote.service;

import com.litrc.votingsystem.vote.dao.ChoiceDAO;
import com.litrc.votingsystem.vote.dao.PollDAO;
import com.litrc.votingsystem.vote.dao.UserDAO;
import com.litrc.votingsystem.vote.dao.VoteDAO;
import com.litrc.votingsystem.vote.exception.ResourceNotFoundException;
import com.litrc.votingsystem.vote.model.Choice;
import com.litrc.votingsystem.vote.model.Poll;
import com.litrc.votingsystem.vote.model.User;
import com.litrc.votingsystem.vote.payload.CreateNewPollForm;
import com.litrc.votingsystem.vote.payload.PollStatusResponse;
import com.litrc.votingsystem.vote.payload.UpdatePollForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    @Autowired
    private PollDAO pollDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private VoteDAO voteDAO;
    @Autowired
    private ChoiceDAO choiceDAO;

    @Autowired
    public void setPollDAO(PollDAO pollDAO) {
        this.pollDAO = pollDAO;
    }

    public Poll findById(Long id) {
        return pollDAO.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.build("Poll", "ID", id));
    }

    public List<Poll> findByUserId(Long userId) {
        return pollDAO.findByUserId(userId);
    }

    public List<Poll> findAll() {
        return pollDAO.findAll();
    }

    public Poll create(String username, CreateNewPollForm form) {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> ResourceNotFoundException.build("User", "Username", username));

        Poll poll = new Poll();
        poll.setQuestion(form.getQuestion());
        poll.setUser(user);
        return pollDAO.save(poll);
    }

    public Poll update(Long id, UpdatePollForm form) {
        Poll poll = pollDAO.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.build("Poll", "ID", id));

        poll.setQuestion(form.getQuestion());
        return pollDAO.save(poll);
    }

    public void delete(Long id) {
        if (!pollDAO.existsById(id)) {
            throw ResourceNotFoundException.build("Poll", "ID", id);
        }
        pollDAO.deleteById(id);
    }

    public boolean existsById(Long id) {
        if (!pollDAO.existsById(id)) {
            throw ResourceNotFoundException.build("Poll", "ID", id);
        }
        return pollDAO.existsById(id);
    }

    public List<PollStatusResponse> responseList(Long id) {
        List<PollStatusResponse> responseList = new ArrayList<>();
        List<Choice> choiceList = choiceDAO.findByPollId(id);
        for (Choice choice : choiceList) {
            Long count = voteDAO.countByChoiceId(choice.getId());

            PollStatusResponse pollStatusResponse = new PollStatusResponse();
            pollStatusResponse.setChoiceId(choice.getId());
            pollStatusResponse.setChoiceCount(count);

            responseList.add(pollStatusResponse);
        }
        return responseList;
    }
}
