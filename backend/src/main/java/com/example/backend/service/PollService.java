package com.example.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.PollRequest;
import com.example.backend.entity.Poll;
import com.example.backend.repository.PollRepository;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    // CREATE POLL

    public Poll createPoll(
            PollRequest request
    ) {

        Poll poll = new Poll();

        // BASIC DETAILS

        poll.setTitle(
                request.getTitle()
        );

        poll.setDescription(
                request.getDescription()
        );

        poll.setType(
                request.getType()
        );

        poll.setCreatedBy(
                request.getCreatedBy()
        );

        // EXPIRY DATE

        poll.setExpiryDate(
                request.getExpiryDate()
        );

        // OPTIONS

        poll.setOptions(
                request.getOptions()
        );

        // INITIAL VOTES

        List<Integer> initialVotes =
                new ArrayList<>();

        for(

                int i = 0;

                i < request.getOptions().size();

                i++
        ) {

            initialVotes.add(0);
        }

        poll.setVotes(initialVotes);

        // PRIVATE EMAILS

        if(
                request.getType()
                        .equals("PRIVATE")
        ) {

            poll.setAllowedEmails(
                    request.getAllowedEmails()
            );
        }

        return pollRepository.save(poll);
    }

    // GET SINGLE POLL

    public Poll getPollById(
            Long id
    ) {

        return pollRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Poll Not Found"
                        )
                );
    }

    // CREATOR POLLS

    public List<Poll> getCreatorPolls(
            String email
    ) {

        return pollRepository
                .findByCreatedBy(email);
    }

    // DELETE POLL

    public void deletePoll(
            Long id
    ) {

        pollRepository.deleteById(id);
    }

    // UPDATE POLL

    public Poll updatePoll(

            Long id,

            PollRequest request
    ) {

        Poll poll =

                pollRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Poll Not Found"
                                )
                        );

        // UPDATE BASIC DETAILS

        poll.setTitle(
                request.getTitle()
        );

        poll.setDescription(
                request.getDescription()
        );

        poll.setType(
                request.getType()
        );

        poll.setExpiryDate(
                request.getExpiryDate()
        );

        // UPDATE OPTIONS

        poll.setOptions(
                request.getOptions()
        );

        // FIX VOTES SIZE

        List<Integer> updatedVotes =
                new ArrayList<>();

        // KEEP OLD VOTES

        if(
                poll.getVotes() != null
        ) {

            for(

                    int i = 0;

                    i < request.getOptions().size();

                    i++
            ) {

                if(
                        i < poll.getVotes().size()
                ) {

                    updatedVotes.add(

                            poll.getVotes()
                                    .get(i)
                    );
                }

                else {

                    updatedVotes.add(0);
                }
            }
        }

        else {

            for(

                    int i = 0;

                    i < request.getOptions().size();

                    i++
            ) {

                updatedVotes.add(0);
            }
        }

        poll.setVotes(updatedVotes);

        // PRIVATE EMAILS

        if(
                request.getType()
                        .equals("PRIVATE")
        ) {

            poll.setAllowedEmails(
                    request.getAllowedEmails()
            );
        }

        else {

            poll.setAllowedEmails(
                    null
            );
        }

        return pollRepository.save(poll);
    }

    // VISIBLE POLLS

    public List<Poll> getVisiblePolls(
            String email
    ) {

        List<Poll> allPolls =
                pollRepository.findAll();

        List<Poll> visiblePolls =
                new ArrayList<>();

        for(Poll poll : allPolls) {

            // PUBLIC POLL

            if(
                    poll.getType()
                            .equals("PUBLIC")
            ) {

                visiblePolls.add(poll);
            }

            // PRIVATE POLL

            else if(

                    poll.getAllowedEmails()
                            != null

                            &&

                            poll.getAllowedEmails()
                                    .contains(email)
            ) {

                visiblePolls.add(poll);
            }
        }

        return visiblePolls;
    }

    // GET PUBLIC POLLS

    public List<Poll> getPublicPolls() {

        return pollRepository
                .findByType("PUBLIC");
    }

    // VOTE POLL

public Poll votePoll(

        Long pollId,

        int optionIndex,

        String userEmail
) {

    Poll poll =

            pollRepository
                    .findById(pollId)
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Poll Not Found"
                            )
                    );

    // CHECK EXPIRY

    if(

            poll.getExpiryDate()
                    .isBefore(
                            LocalDateTime.now()
                    )
    ) {

        throw new RuntimeException(
                "Poll Expired"
        );
    }

    // INITIALIZE VOTED USERS

    if(
            poll.getVotedUsers()
                    == null
    ) {

        poll.setVotedUsers(
                new ArrayList<>()
        );
    }

    // CHECK ALREADY VOTED

    if(

            poll.getVotedUsers()
                    .contains(userEmail)
    ) {

        throw new RuntimeException(
                "You already voted for this poll"
        );
    }

    // GET VOTES

    List<Integer> votes =
            poll.getVotes();

    // INCREMENT VOTE

    votes.set(

            optionIndex,

            votes.get(optionIndex) + 1
    );

    poll.setVotes(votes);

    // SAVE USER EMAIL

    poll.getVotedUsers()
            .add(userEmail);

    return pollRepository.save(poll);
}

    // PRIVATE POLLS

public List<Poll> getPrivatePolls(
        String email
) {

    List<Poll> allPolls =
            pollRepository.findAll();

    List<Poll> privatePolls =
            new ArrayList<>();

    for(Poll poll : allPolls) {

        // ONLY PRIVATE POLLS

        if(

            poll.getType() != null

            &&

            poll.getType()
                    .equalsIgnoreCase("PRIVATE")
        ) {

            // ALLOWED USER

            boolean isAllowedUser =

                    poll.getAllowedEmails()
                            != null

                    &&

                    poll.getAllowedEmails()
                            .contains(email);

            // CREATOR

            boolean isCreator =

                    poll.getCreatedBy()
                            != null

                    &&

                    poll.getCreatedBy()
                            .equalsIgnoreCase(email);

            // SHOW IF CREATOR OR ALLOWED USER

            if(
                    isAllowedUser
                    ||
                    isCreator
            ) {

                privatePolls.add(poll);
            }
        }
    }

    return privatePolls;
}
}