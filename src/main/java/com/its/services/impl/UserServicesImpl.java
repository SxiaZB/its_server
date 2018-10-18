package com.its.services.impl;

import com.its.common.ErrorInfo;
import com.its.dao.impl.UserRDB;
import com.its.dao.impl.UserWDB;
import com.its.entity.Comment;
import com.its.entity.Follow;
import com.its.entity.LoveNote;
import com.its.entity.UpNote;
import com.its.services.IUserServices;
import com.its.utils.json.JSonUtils;
import j.m.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements IUserServices {

    static final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    /**
     * 添加关注
     * @param follow
     * @return
     */
    @Override
    public Result addFollow(Follow follow) {
        Result result = new Result();
        String delSql ="DELETE FROM follow_map  WHERE user_id='"+follow.getUserId()+"' AND follow_pet_id="+follow.getPetId();
        UserWDB.delFollowDao(delSql);
        String addSql = "INSERT INTO follow_map (user_id,follow_pet_id,creater_time)VALUES (?,?,now())";
        Object [] params = {
                follow.getUserId(),
                follow.getPetId()
        };
        boolean bool = UserWDB.addFollowDao(params,addSql);
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
        }
        return result;
    }

    /**
     * 取消关注
     * @param follow
     * @return
     */
    @Override
    public Result delFollow(Follow follow) {
        Result result = new Result();
        String delSql ="DELETE FROM follow_map  WHERE user_id='"+follow.getUserId()+"' AND follow_pet_id="+follow.getPetId();
        boolean bool = UserWDB.delFollowDao(delSql);
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
        }
        return result;
    }

    /**
     * 添加赞
     * @param upNote
     * @return
     */
    @Override
    public Result addUp(UpNote upNote) {
        Result result = new Result();
        String delSql ="DELETE FROM up_note_map  WHERE user_id='"+upNote.getUserId()+"' AND pet_note_id="+upNote.getPetNoteId();
        UserWDB.delUpNoteDao(delSql);
        String addSql = "INSERT INTO up_note_map (user_id,pet_note_id,creater_time)VALUES (?,?,now())";
        Object [] params = {
                upNote.getUserId(),
                upNote.getPetNoteId()
        };
        boolean bool = UserWDB.addFollowDao(params,addSql);
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
        }
        return result;
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Override
    public Result addComment(Comment comment) {
        Result result = new Result();
        String addSql = "INSERT INTO comment_note_map (comment_user_id,pet_note_id,user_name,comment_text,creater_time)VALUES (?,?,?,?,now())";
        Object [] params = {
                comment.getUserId(),
                comment.getPetNoteId(),
                comment.getUserName(),
                comment.getComment()
        };
        boolean bool = UserWDB.addCommentDao(params,addSql);
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
        }
        return result;
    }

    /**
     * 添加收藏
     * @param loveNote
     * @return
     */
    @Override
    public Result addLoveNote(LoveNote loveNote) {
        Result result = new Result();
        String delSql ="DELETE FROM love_note_map  WHERE user_id='"+loveNote.getUserId()+"' AND pet_note_id="+loveNote.getPetNoteId();
        UserWDB.delLoveNoteDao(delSql);
        String addSql = "INSERT INTO love_note_map (user_id,pet_note_id,creater_time)VALUES (?,?,now())";
        Object [] params = {
                loveNote.getUserId(),
                loveNote.getPetNoteId()
        };
        boolean bool = UserWDB.addLoveNoteDao(params,addSql);
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
        }
        return result;
    }

    /**
     * 取消收藏
     * @param loveNote
     * @return
     */
    @Override
    public Result delLoveNote(LoveNote loveNote) {
        Result result = new Result();
        String delSql ="DELETE FROM love_note_map  WHERE user_id='"+loveNote.getUserId()+"' AND pet_note_id="+loveNote.getPetNoteId();
        boolean bool = UserWDB.delLoveNoteDao(delSql);
        if (!bool) {
            result.setErrorCode(ErrorInfo.FAILURE_ERROR.getValue());
            result.setErrorMessage(ErrorInfo.FAILURE_ERROR.getMsg());
        }
        return result;
    }

    /**
     * 用户评论列表
     * @param comment
     * @return
     */
    @Override
    public Result listCommentUser(Comment comment) {
        Result result = new Result();
        String sqlList ="SELECT cnm.* FROM comment_note_map cnm LEFT JOIN" +
                " pet_note pn ON cnm.pet_note_id=pn.pet_note_id WHERE pn.user_id ='"+comment.getUserId()
                +"' ORDER BY cnm.creater_time DESC";
        logger.info("====listCommentUser==== sqlList：" + sqlList);
        List listPetNote = UserRDB.listCommentUserDao(sqlList);
        logger.info("====listCommentUser==== sqlList：" + JSonUtils.toJson(listPetNote));
        result.put("data", listPetNote);
        return result;
    }

    /**
     * 收藏列表
     * @param loveNote
     * @return
     */
    @Override
    public Result listLoveUser(LoveNote loveNote) {
        Result result = new Result();
        String sqlList ="SELECT pn.*, p.pet_user_img_url,p.pet_name,p.pet_grade,p.follow_name," +
                "CASE WHEN fm.follow_pet_id = pn.pet_id THEN 1 ELSE 0 END AS follow_status" +
                " FROM((love_note_map lnm LEFT JOIN pet_note pn ON lnm.pet_note_id = pn.pet_note_id)" +
                "LEFT JOIN follow_map fm ON fm.user_id = '"+loveNote.getUserId()+
                "' AND pn.pet_id = fm.follow_pet_id)LEFT JOIN pet p ON p.pet_id = pn.pet_id" +
                " WHERE lnm.user_id = '"+loveNote.getUserId()+"' ORDER BY lnm.creater_time DESC";
        logger.info("====listLoveUser==== sqlList：" + sqlList);
        List listPetNote = UserRDB.listCommentUserDao(sqlList);
        logger.info("====listLoveUser==== sqlList：" + JSonUtils.toJson(listPetNote));
        result.put("data", listPetNote);
        return result;
    }

    /**
     * 关注宠物列表
     * @param follow
     * @return
     */
    @Override
    public Result listFollowUser(Follow follow) {
        Result result = new Result();
        String sqlList ="SELECT p.pet_id,p.pet_user_img_url,p.pet_name,p.pet_grade," +
                "mp.up_sum,mp.follow_sum,p.follow_name,CASE WHEN 1=1 THEN 1 ELSE 0 END AS follow_status" +
                " FROM(mcb_pet mp LEFT JOIN pet p ON mp.pet_id = p.pet_id)LEFT JOIN" +
                " follow_map fm ON mp.pet_id = fm.follow_pet_id" +
                " WHERE fm.user_id ='"+follow.getUserId()+"' ORDER BY fm.creater_time DESC";
        logger.info("====listFollowUser==== sqlList：" + sqlList);
        List listPetNote = UserRDB.listCommentUserDao(sqlList);
        logger.info("====listFollowUser==== sqlList：" + JSonUtils.toJson(listPetNote));
        result.put("data", listPetNote);
        return result;
    }
}
