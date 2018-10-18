package com.its.entity;

public class Comment {
    private String userId;
    private String userName;
    private int petNoteId;
    private String comment;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPetNoteId() {
        return petNoteId;
    }

    public void setPetNoteId(int petNoteId) {
        this.petNoteId = petNoteId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
