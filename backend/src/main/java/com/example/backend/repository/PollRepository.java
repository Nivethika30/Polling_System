package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Poll;

public interface PollRepository
        extends JpaRepository<Poll, Long> {

    // CREATOR POLLS
    List<Poll> findByCreatedBy(String createdBy);

    // PUBLIC POLLS
    List<Poll> findByType(String type);
}