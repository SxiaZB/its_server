package com.its.services;

import com.its.entity.Comment;
import com.its.entity.User;
import com.its.utils.PageModel;
import j.m.Result;

import javax.servlet.http.HttpServletRequest;

public interface IPetNoteServices {

    /**
     * 获取我的宠物列表
     * @param comment
     * @return
     */
    Result onePetNote(Comment comment);

    /**
     * 获取我的宠物列表
     * @param pageModel
     * @return
     */
    Result listMYpetNote(PageModel<User> pageModel);

    /**
     * 获取关注宠物列表
     * @param pageModel
     * @return
     */
    Result listGZpetNote(PageModel<User> pageModel);

    /**
     * 获取热门宠物列表
     * @param pageModel
     * @return
     */
    Result listRMpetNote(PageModel<User> pageModel);

    /**
     * 添加宠物记录
     * @param request
     * @return
     */
    Result addPetNote(HttpServletRequest request);

    /**
     * 获取帖子评论列表
     * @param comment
     * @return
     */
    Result listCommentNote(Comment comment);
}
