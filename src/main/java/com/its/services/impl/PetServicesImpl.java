package com.its.services.impl;

import com.its.common.ErrorInfo;
import com.its.dao.impl.PetNoteWDB;
import com.its.dao.impl.PetRDB;
import com.its.dao.impl.PetWDB;
import com.its.entity.Pet;
import com.its.services.IPetServices;
import com.its.utils.json.JSonUtils;
import j.m.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PetServicesImpl implements IPetServices {

    static final Logger logger = LoggerFactory.getLogger(PetServicesImpl.class);
    SimpleDateFormat imgDateFormat = new SimpleDateFormat("HHmmssyyyyMMdd");// HH:mm:ss

    @Override
    public Result addPetUser(HttpServletRequest request) {
        Result result = new Result();
        String contentType = request.getContentType();
        boolean bool = false;
        if (null != contentType && contentType.toLowerCase().startsWith("multipart/form-data")) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile sourceFile = multipartRequest.getFile("file");
            if (null != sourceFile && !sourceFile.isEmpty()) {
                String fileName = "a" + multipartRequest.getParameter("userId") + "p" + imgDateFormat.format(new Date());
                String[] fileContentType = sourceFile.getOriginalFilename().split("\\.");
                if (fileContentType != null && fileContentType.length > 0) {
                    String fileType = fileContentType[fileContentType.length - 1];
                    String pathName = "./webapps/its_imgs/" + fileName + "." + fileType;
                    String url = request.getServerName();
                    String port = ":" + request.getServerPort();
                    File targetFile = new File(pathName);
                    targetFile.mkdirs();
                    try {
                        sourceFile.transferTo(targetFile);
                    } catch (IOException e) {
                        result.setErrorCode(ErrorInfo.DEFAULT_ERROR.getValue());
                        result.setErrorMessage(ErrorInfo.DEFAULT_ERROR.getMsg());
                        return result;
                    }
                    String sqlCode = "SELECT COUNT(pet_id)  AS pet_sum FROM pet WHERE user_id ='" + multipartRequest.getParameter("userId") + "'";
                    String petSum = PetRDB.petSumDao(sqlCode);
                    String num = petSum;
                    for (int i = petSum.length(); i < 4; i++) {
                        num = "0" + num;
                    }
                    String sql = "INSERT INTO pet (user_id,pet_code,pet_name,pet_user_img_url," +
                            "pet_sex,pet_breed,pet_year,pet_month,pet_day,pet_grade,pet_value,follow_name,pet_bz," +
                            "`status`,creater_by,creater_time,lastmodify_by,lastmodify_time)" +
                            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,NOW(),?,NOW())";
                    Object[] params = {
                            multipartRequest.getParameter("userId"),
                            multipartRequest.getParameter("userId") + num,
                            multipartRequest.getParameter("petName"),
                            "http://" + url + port + "/its_imgs/" + fileName + "." + fileType,
                            multipartRequest.getParameter("petSex"),
                            multipartRequest.getParameter("petBreed"),
                            multipartRequest.getParameter("petYear"),
                            multipartRequest.getParameter("petMonth"),
                            multipartRequest.getParameter("petDay"),
                            multipartRequest.getParameter("petGrade"),
                            multipartRequest.getParameter("petValue"),
                            multipartRequest.getParameter("followName"),
                            multipartRequest.getParameter("petBZ"),
                            multipartRequest.getParameter("userId"),
                            multipartRequest.getParameter("userId")
                    };
                    logger.info("====addPet==== params：" + JSonUtils.toJson(params));
                    bool = PetWDB.addPetDao(params,sql);
                }
            }
        }
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
            logger.info("====PetServicesImpl==== addPet：异常结束" + JSonUtils.toJson(result));
        }
        return result;
    }

    @Override
    public Result updataPetUser(HttpServletRequest request) {
        Result result = new Result();
        String contentType = request.getContentType();
        boolean bool = false;
        if (null != contentType && contentType.toLowerCase().startsWith("multipart/form-data")) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile sourceFile = multipartRequest.getFile("file");
            if (null != sourceFile && !sourceFile.isEmpty()) {
                String fileName = "a" + multipartRequest.getParameter("userId") + "p" + imgDateFormat.format(new Date());
                String[] fileContentType = sourceFile.getOriginalFilename().split("\\.");
                if (fileContentType != null && fileContentType.length > 0) {
                    String fileType = fileContentType[fileContentType.length - 1];
                    String pathName = "./webapps/its_imgs/" + fileName + "." + fileType;
                    String url = request.getServerName();
                    String port = ":" + request.getServerPort();
                    File targetFile = new File(pathName);
                    targetFile.mkdirs();
                    try {
                        sourceFile.transferTo(targetFile);
                    } catch (IOException e) {
                        result.setErrorCode(ErrorInfo.DEFAULT_ERROR.getValue());
                        result.setErrorMessage(ErrorInfo.DEFAULT_ERROR.getMsg());
                        return result;
                    }
                    Object[] params = {
                            multipartRequest.getParameter("userId"),
                            "http://" + url + port + "/its_imgs/" + fileName + "." + fileType,
                            multipartRequest.getParameter("petName"),
                            multipartRequest.getParameter("petSex"),
                            multipartRequest.getParameter("petBreed"),
                            multipartRequest.getParameter("petYear"),
                            multipartRequest.getParameter("petMonth"),
                            multipartRequest.getParameter("petDay"),
                            multipartRequest.getParameter("petBZ"),
                            multipartRequest.getParameter("followName"),
                            multipartRequest.getParameter("petId")
                    };
                    String sql = "UPDATE pet p SET p.lastmodify_time=NOW()," +
                            "p.pet_user_img_url='"+params[1]+"',p.pet_name='"+params[2]+"'," +
                            "p.pet_sex='"+params[3]+"',p.pet_breed='"+params[4]+"',p.pet_year='"+params[5]+"'," +
                            "p.pet_month='"+params[6]+"',p.pet_day='"+params[7]+"',p.pet_bz='"+params[8]+"'," +
                            "p.follow_name='"+params[9]+"' WHERE p.pet_id="+params[10];
                    logger.info("====updataPetUser==== params：" + JSonUtils.toJson(params));
                    bool = PetWDB.updataPetDao(sql);
                }
            }else {
                Object[] params = {
                        multipartRequest.getParameter("userId"),
                        multipartRequest.getParameter("petName"),
                        multipartRequest.getParameter("petSex"),
                        multipartRequest.getParameter("petBreed"),
                        multipartRequest.getParameter("petYear"),
                        multipartRequest.getParameter("petMonth"),
                        multipartRequest.getParameter("petDay"),
                        multipartRequest.getParameter("petBZ"),
                        multipartRequest.getParameter("followName"),
                        multipartRequest.getParameter("petId")
                };
                String sql = "UPDATE pet p SET p.lastmodify_time=NOW()," +
                        "p.pet_name='"+params[1]+"'," +
                        "p.pet_sex='"+params[2]+"',p.pet_breed='"+params[3]+"',p.pet_year='"+params[4]+"'," +
                        "p.pet_month='"+params[5]+"',p.pet_day='"+params[6]+"',p.pet_bz='"+params[7]+"'," +
                        "p.follow_name='"+params[8]+"' WHERE p.pet_id="+params[9];
                logger.info("====updataPetUser==== params：" + JSonUtils.toJson(params));
                bool = PetWDB.updataPetDao(sql);
            }
        }
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
            logger.info("====PetServicesImpl==== updataPetUser：异常结束" + JSonUtils.toJson(result));
        }
        return result;
    }

    @Override
    public Result delPetUser(Pet pet) {
        return null;
    }

    /**
     * 获取某用户的宠物列表
     *
     * @param pet
     * @return
     */
    @Override
    public Result listPetUser(Pet pet) {
        Result result = new Result();
        String sql = "SELECT * FROM pet p WHERE p.user_id='" + pet.getUserId() + "'";
        logger.info("====PetServicesImpl==== listPetUser：sql" + sql);
        List list = PetRDB.listPetDao(sql);
        logger.info("====PetServicesImpl==== listPetUser：list" + JSonUtils.toJson(list));
        result.put("data", list);
        return result;
    }


    @Override
    public Result mcbListPetUser() {
        return null;
    }

    @Override
    public Result updataPetImg(HttpServletRequest request) {
        return null;
    }
}
