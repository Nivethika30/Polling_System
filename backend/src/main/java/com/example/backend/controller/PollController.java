package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.PollRequest;
import com.example.backend.entity.Poll;
import com.example.backend.service.PollService;

@RestController
@RequestMapping("/poll")
@CrossOrigin("*")

public class PollController {

    @Autowired
    private PollService pollService;

    // CREATE POLL

    @PostMapping("/create")

    public Poll createPoll(

            @RequestBody PollRequest request
    ) {

        return pollService.createPoll(request);
    }

    // GET SINGLE POLL

    @GetMapping("/{id}")

    public Poll getPollById(

            @PathVariable Long id
    ) {

        return pollService.getPollById(id);
    }

    // CREATOR POLLS

    @GetMapping("/creator/{email}")

    public List<Poll> getCreatorPolls(

            @PathVariable String email
    ) {

        return pollService.getCreatorPolls(email);
    }

    // DELETE POLL

    @DeleteMapping("/delete/{id}")

    public String deletePoll(

            @PathVariable Long id
    ) {

        pollService.deletePoll(id);

        return "Poll Deleted";
    }

    // UPDATE POLL

    @PutMapping("/update/{id}")

    public Poll updatePoll(

            @PathVariable Long id,

            @RequestBody PollRequest request
    ) {

        return pollService.updatePoll(
                id,
                request
        );
    }

    // VISIBLE POLLS

    @GetMapping("/visible/{email}")

    public List<Poll> getVisiblePolls(

            @PathVariable String email
    ) {

        return pollService.getVisiblePolls(email);
    }

    // PUBLIC POLLS

    @GetMapping("/public")

    public List<Poll> getPublicPolls() {

        return pollService.getPublicPolls();
    }

    // VOTE POLL

@PostMapping("/vote/{pollId}/{optionIndex}/{userEmail}")

public Poll votePoll(

        @PathVariable Long pollId,

        @PathVariable int optionIndex,

        @PathVariable String userEmail
) {

    return pollService.votePoll(

            pollId,

            optionIndex,

            userEmail
    );
}
    // PRIVATE POLLS

@GetMapping("/private/{email}")

public List<Poll> getPrivatePolls(

        @PathVariable String email
) {

    return pollService
            .getPrivatePolls(email);
}
}