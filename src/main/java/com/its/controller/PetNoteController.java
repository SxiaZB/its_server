package com.its.controller;

import com.its.entity.Comment;
import com.its.entity.User;
import com.its.services.IPetNoteServices;
import com.its.utils.PageModel;
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

import javax.servlet.http.HttpServletRequest;


/**
 * 功能：宠物笔记
 * 作者：朱志波
 * 时间：2018/5/18 0018
 */
@Controller
@RequestMapping("/petNote")
public class PetNoteController {
    static final Logger log = LoggerFactory.getLogger(PetNoteController.class);

    @Autowired
    private IPetNoteServices petNoteServices;



    /**
     * 一条帖子
     * @param comment
     * @return
     */
    @RequestMapping(value = "/onePetNote", method = RequestMethod.POST)
    @ResponseBody
    public Result onePetNote(@RequestBody Comment comment) {
        log.info("====请求参数 ====："+JSonUtils.toJson(comment));
        Result result = petNoteServices.onePetNote(comment);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 我的宠物笔记列表
     * @param pageModel
     * @return
     */
    @RequestMapping(value = "/listMYpetNote", method = RequestMethod.POST)
    @ResponseBody
    public Result listMYpetNote(@RequestBody PageModel<User> pageModel) {
        log.info("====请求参数 ====："+JSonUtils.toJson(pageModel));
        Result result = petNoteServices.listMYpetNote(pageModel);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }


    /**
     * 关注宠物笔记列表（40条）
     * @param pageModel
     * @return
     */
    @RequestMapping(value = "/listGZpetNote", method = RequestMethod.POST)
    @ResponseBody
    public Result listGZpetNote(@RequestBody PageModel<User> pageModel) {
        log.info("====请求参数 ====："+JSonUtils.toJson(pageModel));
        Result result = petNoteServices.listGZpetNote(pageModel);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 热门宠物笔记列表
     * @param pageModel
     * @return
     */
    @RequestMapping(value = "/listRMpetNote", method = RequestMethod.POST)
    @ResponseBody
    public Result listRMpetNote(@RequestBody PageModel<User> pageModel) {
        log.info("====请求参数 ====："+JSonUtils.toJson(pageModel));
        Result result = petNoteServices.listRMpetNote(pageModel);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 添加宠物记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/addPetNote", method = RequestMethod.POST)
    @ResponseBody
    public Result addPetNote(HttpServletRequest request){
        log.info("====请求参数 ====："+request);
        Result result = petNoteServices.addPetNote(request);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }
    /**
     * 评论列表
     * @param comment
     * @return
     */
    @RequestMapping(value = "/listCommentNote", method = RequestMethod.POST)
    @ResponseBody
    public Result listCommentNote(@RequestBody Comment comment) {
        log.info("====请求参数 ====："+JSonUtils.toJson(comment));
        Result result = petNoteServices.listCommentNote(comment);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }
}
