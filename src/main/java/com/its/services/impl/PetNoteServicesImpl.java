package com.its.services.impl;

import com.its.common.ErrorInfo;
import com.its.dao.impl.PetNoteRDB;
import com.its.dao.impl.PetNoteWDB;
import com.its.entity.Comment;
import com.its.entity.User;
import com.its.services.IPetNoteServices;
import com.its.utils.PageModel;
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
import java.util.Map;

/**
 * 功能：宠物笔记
 * 作者：朱志波
 * 时间：2018/5/18 0018
 */
@Service
public class PetNoteServicesImpl implements IPetNoteServices {

    static final Logger logger = LoggerFactory.getLogger(PetNoteServicesImpl.class);

    SimpleDateFormat imgDateFormat = new SimpleDateFormat("HHmmssyyyyMMdd");// HH:mm:ss

    @Override
    public Result onePetNote(Comment comment) {
        Result result = new Result();
        String sqlNote = "SELECT pn.*,p.pet_user_img_url,p.pet_name,p.pet_grade,p.follow_name" +
                " FROM  pet_note pn LEFT JOIN pet p ON p.pet_id=pn.pet_id" +
                " WHERE pn.pet_note_id ='"+comment.getPetNoteId()+"'";
        logger.info("====onePetNote==== sqlNote：" + sqlNote);
        List listPetNote = PetNoteRDB.listMYpetNoteDao(sqlNote);
        logger.info("====onePetNote==== listPetNote：" + JSonUtils.toJson(listPetNote));
//        pageModel.setList(listPetNote);
        result.put("data", listPetNote);
        return result;
    }

    @Override
    public Result listMYpetNote(PageModel<User> pageModel) {
        Result result = new Result();
        String sqlNote = "SELECT pn.*,p.pet_user_img_url,p.pet_name,p.pet_grade,p.follow_name" +
                " FROM  pet_note pn LEFT JOIN pet p ON p.pet_id=pn.pet_id" +
                " WHERE pn.user_id ='"+pageModel.getModel().getUserId()+"'" +
                " ORDER BY pn.lastmodify_time DESC ";
        logger.info("====listMYpetNote==== sqlNote：" + sqlNote);
        List listPetNote = PetNoteRDB.listMYpetNoteDao(sqlNote);
        logger.info("====listMYpetNote==== listPetNote：" + JSonUtils.toJson(listPetNote));
//        pageModel.setList(listPetNote);
        result.put("data", listPetNote);
        return result;
    }

    /**
     * 关注宠物笔记
     * @param pageModel
     * @return
     */
    @Override
    public Result listGZpetNote(PageModel<User> pageModel) {
        Result result = new Result();
        String sqlNote = "SELECT pn.*,p.pet_user_img_url,p.pet_name,p.pet_grade,p.follow_name," +
                " CASE WHEN 1 =1 THEN 1 END AS follow_status FROM" +
                " ( pet_note pn LEFT JOIN follow_map fm ON pn.pet_id=fm.follow_pet_id )" +
                " LEFT JOIN pet p ON p.pet_id=pn.pet_id WHERE fm.user_id ='" + pageModel.getModel().getUserId() +
                "' ORDER BY pn.lastmodify_time DESC LIMIT 40" ;
        logger.info("====listGZpetNote==== sqlNote：" + sqlNote);
        List listPetNote = PetNoteRDB.listGZpetNoteDao(sqlNote);
        logger.info("====listGZpetNote==== listPetNote：" + JSonUtils.toJson(listPetNote));
//        pageModel.setList(listPetNote);
        result.put("data", listPetNote);
        return result;
    }

    /**
     * 热门宠物笔记
     *
     * @param pageModel
     * @return
     */
    @Override
    public Result listRMpetNote(PageModel<User> pageModel) {
        Result result = new Result();
        String sqlNoteId = "SELECT DISTINCT pn.pet_note_id FROM up_note_map pn" +
                " WHERE pn.pet_note_id >= ((SELECT MAX(pet_note_id) FROM up_note_map )" +
                " - (SELECT MIN(pet_note_id) FROM up_note_map)) * RAND()" +
                " + (SELECT MIN(pet_note_id) FROM up_note_map) LIMIT " + pageModel.getPageSize();
        logger.info("====listRMpetNote==== sqlNoteId：" + sqlNoteId);
        List petIds = PetNoteRDB.listRMpetNoteDao(sqlNoteId);
        logger.info("====listRMpetNote==== petIds：" + JSonUtils.toJson(petIds));
        String sqlList = getListRMsql(pageModel.getModel(), petIds);
        logger.info("====listRMpetNote==== sqlList：" + sqlList);
        List listPetNote = PetNoteRDB.listRMpetNoteDao(sqlList);
        logger.info("====listRMpetNote==== sqlList：" + JSonUtils.toJson(listPetNote));
//        pageModel.setList(listPetNote);
        result.put("data", listPetNote);
        return result;
    }

    /**
     * 添加宠物记录
     *
     * @param request
     * @return
     */
    @Override
    public Result addPetNote(HttpServletRequest request) {
        Result result = new Result();
        String contentType = request.getContentType();
        boolean bool = false;
        if (null != contentType && contentType.toLowerCase().startsWith("multipart/form-data")) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile sourceFile = multipartRequest.getFile("file");
            if (null != sourceFile && !sourceFile.isEmpty()) {
                String fileName = "a" + multipartRequest.getParameter("userId") + "pn" + imgDateFormat.format(new Date());
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
                            multipartRequest.getParameter("petId"),
                            multipartRequest.getParameter("userId"),
                            "http://" + url + port + "/its_imgs/" + fileName + "." + fileType,
                            multipartRequest.getParameter("noteText"),
                            multipartRequest.getParameter("userId"),
                            multipartRequest.getParameter("userId")
                    };
                    logger.info("====addPetNote==== params：" + JSonUtils.toJson(params));
                    bool = PetNoteWDB.addPetNoteDao(params);
                }
            }
        }
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
            logger.info("====PetNoteServicesImpl==== addPetNote：异常结束" + JSonUtils.toJson(result));
        }
        return result;
    }

    /**
     * 获取记录评论列表
     * @param comment
     * @return
     */
    @Override
    public Result listCommentNote(Comment comment) {
        Result result = new Result();
        String sqlList ="SELECT * FROM comment_note_map cnm WHERE cnm.pet_note_id ="+comment.getPetNoteId();
        logger.info("====listCommentNote==== sqlList：" + sqlList);
        List listPetNote = PetNoteRDB.listCommentNoteDao(sqlList);
        logger.info("====listCommentNote==== sqlList：" + JSonUtils.toJson(listPetNote));
        result.put("data", listPetNote);
        return result;
    }

    /**
     * 获取热门列表SQL拼接
     *
     * @param user
     * @param petIds
     * @return
     */
    private String getListRMsql(User user, List petIds) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT pn.*,p.pet_user_img_url,p.pet_name,p.pet_grade,p.follow_name," +
                "CASE WHEN fm.follow_pet_id =pn.pet_id THEN 1 ELSE 0 END AS follow_status ");
        sql.append(" FROM (pet_note pn LEFT JOIN follow_map fm ON fm.user_id='" + user.getUserId()
                + "' AND pn.pet_id=fm.follow_pet_id )LEFT JOIN pet p ON p.pet_id=pn.pet_id" +
                " WHERE pn.pet_note_id IN (");
        for (int i = 0; petIds.size() > i; i++) {
            Map id = (Map) petIds.get(i);
            sql.append(id.get("pet_note_id") + ",");
        }
        sql.append("0)");
        return sql.toString();
    }
}
