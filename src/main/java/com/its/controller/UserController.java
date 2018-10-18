package com.its.controller;

import com.its.entity.Comment;
import com.its.entity.Follow;
import com.its.entity.LoveNote;
import com.its.entity.UpNote;
import com.its.services.IUserServices;
import com.its.utils.json.JSonUtils;
import j.m.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserServices userServices;

    /**
     * 添加关注
     *
     * @param follow
     * @return
     */
    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    @ResponseBody
    public Result addFollow(@RequestBody Follow follow) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(follow));
        Result result = userServices.addFollow(follow);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 取消关注
     *
     * @param follow
     * @return
     */
    @RequestMapping(value = "/delFollow", method = RequestMethod.POST)
    @ResponseBody
    public Result delFollow(@RequestBody Follow follow) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(follow));
        Result result = userServices.delFollow(follow);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 添加赞
     *
     * @param upNote
     * @return
     */
    @RequestMapping(value = "/addUpNote", method = RequestMethod.POST)
    @ResponseBody
    public Result addUpNote(@RequestBody UpNote upNote) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(upNote));
        Result result = userServices.addUp(upNote);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(@RequestBody Comment comment) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(comment));
        Result result = userServices.addComment(comment);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 添加收藏
     *
     * @param loveNote
     * @return
     */
    @RequestMapping(value = "/addLoveNote", method = RequestMethod.POST)
    @ResponseBody
    public Result addLoveNote(@RequestBody LoveNote loveNote) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(loveNote));
        Result result = userServices.addLoveNote(loveNote);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 取消收藏
     *
     * @param loveNote
     * @return
     */
    @RequestMapping(value = "/delLoveNote", method = RequestMethod.POST)
    @ResponseBody
    public Result delLoveNote(@RequestBody LoveNote loveNote) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(loveNote));
        Result result = userServices.delLoveNote(loveNote);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 评论列表
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/listCommentUser", method = RequestMethod.POST)
    @ResponseBody
    public Result listCommentUser(@RequestBody Comment comment) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(comment));
        Result result = userServices.listCommentUser(comment);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 收藏列表
     *
     * @param loveNote
     * @return
     */
    @RequestMapping(value = "/listLoveUser", method = RequestMethod.POST)
    @ResponseBody
    public Result listLoveUser(@RequestBody LoveNote loveNote) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(loveNote));
        Result result = userServices.listLoveUser(loveNote);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 关注宠物列表
     *
     * @param follow
     * @return
     */
    @RequestMapping(value = "/listFollowUser", method = RequestMethod.POST)
    @ResponseBody
    public Result listFollowUser(@RequestBody Follow follow) {
        log.info("====请求参数 ====：" + JSonUtils.toJson(follow));
        Result result = userServices.listFollowUser(follow);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }
}
