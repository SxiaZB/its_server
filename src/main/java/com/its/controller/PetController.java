package com.its.controller;

import com.its.entity.Pet;
import com.its.services.IPetServices;
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
 * 功能：宠物
 * 作者：朱志波
 * 时间：2018/5/21 0021
 */
@Controller
@RequestMapping("/pet")
public class PetController {
    static final Logger log = LoggerFactory.getLogger(PetController.class);
    @Autowired
    private IPetServices petServices;

    /**
     * 删除宠物
     * @param @RequestBody
     * @return
     */
    @RequestMapping(value = "/delPet", method = RequestMethod.POST)
    @ResponseBody
    public Result delPet(@RequestBody Pet pet){
        log.info("====请求参数 ====：request"+pet);
        Result result = petServices.delPetUser(pet);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 修改宠物信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/updataPet", method = RequestMethod.POST)
    @ResponseBody
    public Result updataPet(HttpServletRequest request){
        log.info("====请求参数 ====：request"+request);
        Result result = petServices.updataPetUser(request);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 添加宠物
     * @param request
     * @return
     */
    @RequestMapping(value = "/addPet", method = RequestMethod.POST)
    @ResponseBody
    public Result addPet(HttpServletRequest request){
        log.info("====请求参数 ====：request"+request);
        Result result = petServices.addPetUser(request);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }

    /**
     * 获取某用户的宠物列表
     * @param pet
     * @return
     */
    @RequestMapping(value = "/listPetUser", method = RequestMethod.POST)
    @ResponseBody
    public Result listPetUser(@RequestBody Pet pet){
        log.info("====请求参数 ====："+ JSonUtils.toJson(pet));
        Result result = petServices.listPetUser(pet);
        log.info("====返回参数：" + JSonUtils.toJson(result));
        return result;
    }
}
