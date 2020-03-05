package com.litrc.votingsystem.vote.service;

import com.litrc.votingsystem.vote.dao.ChoiceDAO;
import com.litrc.votingsystem.vote.dao.PollDAO;
import com.litrc.votingsystem.vote.dao.UserDAO;
import com.litrc.votingsystem.vote.dao.VoteDAO;
import com.litrc.votingsystem.vote.exception.ResourceNotFoundException;
import com.litrc.votingsystem.vote.model.Choice;
import com.litrc.votingsystem.vote.model.Poll;
import com.litrc.votingsystem.vote.model.User;
import com.litrc.votingsystem.vote.model.Vote;
import com.litrc.votingsystem.vote.payload.CreateNewVoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    @Autowired
    private VoteDAO voteDAO;
    @Autowired
    private UserDAO userDao;
    @Autowired
    private ChoiceDAO choiceDao;
    @Autowired
    private PollDAO pollDAO;

    @Autowired
    public void setVoteDAO(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    public boolean existsByPollIdAndUsername(Long pollId, String username) {
        return voteDAO.existsByPollIdAndUser_Username(pollId, username);
    }

    public Vote create(Long pollId, String username, CreateNewVoteForm form) {
        if (existsByPollIdAndUsername(pollId, username)) {
            throw new RuntimeException("不能重复投票");
        }

        User user = userDao.findByUsername(username)
                .orElseThrow(() -> ResourceNotFoundException.build("User", "Username", username));
        Poll poll = pollDAO.findById(pollId)
                .orElseThrow(() -> ResourceNotFoundException.build("Poll", "ID", pollId));
        Choice choice = choiceDao.findById(form.getChoiceId())
                .orElseThrow(() -> ResourceNotFoundException.build("Choice", "ID", form.getChoiceId()));

        Vote vote = new Vote();
        vote.setUser(user);
        vote.setPoll(poll);
        vote.setChoice(choice);
        return voteDAO.save(vote);
    }

//    public Long countByChoiceId(Long choiceId) {
//        return voteDAO.countByChoiceId(choiceId);
//    }

}
