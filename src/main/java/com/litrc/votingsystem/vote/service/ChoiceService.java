package com.litrc.votingsystem.vote.service;

import com.litrc.votingsystem.vote.dao.ChoiceDAO;
import com.litrc.votingsystem.vote.dao.PollDAO;
import com.litrc.votingsystem.vote.exception.ResourceNotFoundException;
import com.litrc.votingsystem.vote.model.Choice;
import com.litrc.votingsystem.vote.model.Poll;
import com.litrc.votingsystem.vote.payload.CreateNewChoiceForm;
import com.litrc.votingsystem.vote.payload.UpdateChoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoiceService {
    @Autowired
    private ChoiceDAO choiceDAO;
    @Autowired
    private PollDAO pollDAO;

    @Autowired
    public void setChoiceDAO(ChoiceDAO choiceDAO) {
        this.choiceDAO = choiceDAO;
    }

    public Optional<Choice> findById(Long id) {
        return choiceDAO.findById(id);
    }

    public List<Choice> findByPollId(Long pollId) {
        return choiceDAO.findByPollId(pollId);
    }

    public Choice create(Long pollId, CreateNewChoiceForm form) {
        Poll poll = pollDAO.findById(pollId)
                .orElseThrow(() -> ResourceNotFoundException.build("User", "Username", pollId));

        Choice choice = new Choice();
        choice.setText(form.getText());
        choice.setPoll(poll);
        return choiceDAO.save(choice);
    }

    public Choice update(Long id, Long pollId, UpdateChoiceForm form) {
        if (!pollDAO.existsById(pollId)) {
            throw ResourceNotFoundException.build("Poll", "ID", pollId);
        }

        Choice choice = choiceDAO.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.build("Choice", "ID", id));

        choice.setText(form.getText());
        return choiceDAO.save(choice);
    }

    public boolean existsById(Long id) {
        return choiceDAO.existsById(id);
    }

    public void delete(Long pollId, Long id) {
        if (!pollDAO.existsById(pollId)) {
            throw ResourceNotFoundException.build("Poll", "ID", pollId);
        }

        if (!choiceDAO.existsById(id)) {
            throw ResourceNotFoundException.build("Choice", "ID", id);
        }
        choiceDAO.deleteById(id);
    }
}
