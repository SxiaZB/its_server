package com.its.entity;

import java.util.Date;

public class Pet {
    private int petId;
    private String userId;
    private String petCode;
    private String petName;
    private String petUserImgUrl;
    private String petBreed;
    private String petGrade;
    private long petValue;
    private String followName;
    private int status;//状态
    private Date creationDate; //创建时间
    private String creationBy; //创建人
    private Date lastModifiedDate; //最后修改时间
    private String lastModifiedBy; //修改人

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetUserImgUrl() {
        return petUserImgUrl;
    }

    public void setPetUserImgUrl(String petUserImgUrl) {
        this.petUserImgUrl = petUserImgUrl;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetGrade() {
        return petGrade;
    }

    public void setPetGrade(String petGrade) {
        this.petGrade = petGrade;
    }

    public long getPetValue() {
        return petValue;
    }

    public void setPetValue(long petValue) {
        this.petValue = petValue;
    }

    public String getFollowName() {
        return followName;
    }

    public void setFollowName(String followName) {
        this.followName = followName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getCreationBy() {
        return creationBy;
    }

    public void setCreationBy(String creationBy) {
        this.creationBy = creationBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
