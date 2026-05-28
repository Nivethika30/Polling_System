package com.example.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PollRequest {

    private String title;

    private String description;

    private String type;

    private String createdBy;

    // EXPIRY DATE

    private LocalDateTime expiryDate;

    // POLL OPTIONS

    private List<String> options;

    // PRIVATE EMAILS

    private List<String> allowedEmails;

    // VOTED USERS

    private List<String> votedUsers;

    // GETTERS & SETTERS

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

    public List<String> getAllowedEmails() {

        return allowedEmails;
    }

    public void setAllowedEmails(
            List<String> allowedEmails
    ) {

        this.allowedEmails = allowedEmails;
    }

    public List<String> getVotedUsers() {

        return votedUsers;
    }

    public void setVotedUsers(
            List<String> votedUsers
    ) {

        this.votedUsers = votedUsers;
    }
}