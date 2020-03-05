package com.litrc.votingsystem.vote.dao;

import com.litrc.votingsystem.vote.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollDAO extends JpaRepository<Poll, Long> {
    List<Poll> findByUserId(Long userId);

}
