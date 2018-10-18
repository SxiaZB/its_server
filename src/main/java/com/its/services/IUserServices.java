package com.its.services;

import com.its.entity.Comment;
import com.its.entity.Follow;
import com.its.entity.LoveNote;
import com.its.entity.UpNote;
import j.m.Result;

public interface IUserServices {
    Result addFollow(Follow follow);
    Result delFollow(Follow follow);
    Result addUp(UpNote upNote);
    Result addComment(Comment comment);
    Result addLoveNote(LoveNote loveNote);
    Result delLoveNote(LoveNote loveNote);
    Result listCommentUser(Comment comment);
    Result listLoveUser(LoveNote loveNote);
    Result listFollowUser(Follow follow);
}
