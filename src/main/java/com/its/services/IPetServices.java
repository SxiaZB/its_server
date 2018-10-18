package com.its.services;

import com.its.entity.Pet;
import j.m.Result;

import javax.servlet.http.HttpServletRequest;

public interface IPetServices {
    /**
     * 添加新宠
     * @param request
     * @return
     */
    Result addPetUser (HttpServletRequest request);

    /**
     * 修改宠物基本信息
     * @param request
     * @return
     */
    Result updataPetUser(HttpServletRequest request);

    /**
     * 删除宠物
     * @param pet
     * @return
     */
    Result delPetUser(Pet pet);

    /**
     * 获取某用户宠物列表
     * @param pet
     * @return
     */
    Result listPetUser(Pet pet);

    /**
     * 名宠榜列表
     * @return
     */
    Result mcbListPetUser();

    /**
     * 修改宠物头像
     * @param request
     * @return
     */
    Result updataPetImg(HttpServletRequest request);
}
