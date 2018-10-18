package com.its.controller;

import com.its.entity.User;
import com.its.services.IMcbServices;
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
@RequestMapping("/mcb")
public class McbController {
    static final Logger log = LoggerFactory.getLogger(McbController.class);
    @Autowired
    private IMcbServices mcbServices;
    /**
     * 获取某用户的宠物列表
     * @param user
     * @return
     */
    @RequestMapping(value = "/listMcb", method = RequestMethod.POST)
    @ResponseBody
    public Result listMcb(@RequestBody User user){
        log.info("====请求参数 ====："+ JSonUtils.toJson(user));
        Result result = mcbServices.getMcb(user);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }
}
