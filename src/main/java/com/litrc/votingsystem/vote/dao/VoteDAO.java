package com.litrc.votingsystem.vote.dao;

import com.litrc.votingsystem.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDAO extends JpaRepository<Vote, Long> {
    boolean existsByPollIdAndUser_Username(Long pollId, String username);

    Long countByChoiceId(Long choiceId);
}
