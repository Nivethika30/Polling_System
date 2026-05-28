package com.example.backend.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Poll {

    @Id

    @GeneratedValue(
            strategy =
            GenerationType.IDENTITY
    )

    private Long id;

    // BASIC DETAILS

    private String title;

    private String description;

    // PUBLIC / PRIVATE

    private String type;

    // CREATOR EMAIL

    private String createdBy;

    // EXPIRY DATE

    private LocalDateTime expiryDate;

    // POLL OPTIONS

    @ElementCollection

    private List<String> options;

    // OPTION VOTES

    @ElementCollection

    private List<Integer> votes;

    // PRIVATE POLL EMAILS

    @ElementCollection

    private List<String> allowedEmails;
    @ElementCollection
private List<String> votedUsers;

    // GETTERS & SETTERS

    public List<String> getVotedUsers() {

    return votedUsers;
}

public void setVotedUsers(
        List<String> votedUsers
) {

    this.votedUsers = votedUsers;
}

    public Long getId() {

        return id;
    }

    public void setId(
            Long id
    ) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(
            String title
    ) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(
            String description
    ) {

        this.description = description;
    }

    public String getType() {

        return type;
    }

    public void setType(
            String type
    ) {

        this.type = type;
    }

    public String getCreatedBy() {

        return createdBy;
    }

    public void setCreatedBy(
            String createdBy
    ) {

        this.createdBy = createdBy;
    }

    public LocalDateTime getExpiryDate() {

        return expiryDate;
    }

    public void setExpiryDate(
            LocalDateTime expiryDate
    ) {

        this.expiryDate = expiryDate;
    }

    public List<String> getOptions() {

        return options;
    }

    public void setOptions(
            List<String> options
    ) {

        this.options = options;
    }

    public List<Integer> getVotes() {

        return votes;
    }

    public void setVotes(
            List<Integer> votes
    ) {

        this.votes = votes;
    }

    public List<String> getAllowedEmails() {

        return allowedEmails;
    }

    public void setAllowedEmails(
            List<String> allowedEmails
    ) {

        this.allowedEmails = allowedEmails;
    }
}