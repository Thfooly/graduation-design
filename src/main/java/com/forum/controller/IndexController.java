package com.forum.controller;

import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.pojo.NoteConstant;
import com.forum.pojo.User;
import com.forum.pojo.UserMsg;
import com.forum.service.IndexService;
import com.forum.service.UserMsgService;
import com.forum.utils.RankTransformUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ApiModel("首页信息显示")
@RestController
@RequestMapping("index")
public class IndexController {
    /**
     * 1.根据登录用户显示对应信息
     * 2.获取轮播图
     * 3.获取最新帖子
     * 4.查询自己发布帖子
     */
    @Autowired
    private IndexService indexService;
    @Autowired
    private UserMsgService userMsgService;

    /**
     * 根据登录用户显示对应信息
     * @return
     */
    @PostMapping("userInfo")
    public ResponseEntity<String > userInfo(@ApiParam("用户id")
                         @RequestBody String uid){
        //没有用户id传入
        if (uid == null){
//            model.addAttribute("loginInfo", false);
            throw new RuntimeCommonException(ExceptionEnums.NOT_LOGIN);
        }
        User user = indexService.findByUid(uid);
        UserMsg userMsg = userMsgService.findByUserId(user.getId());
//        model.addAttribute("rank", RankTransformUtils.transform(userMsg.getUserId()));
        return ResponseEntity.ok(RankTransformUtils.transform(userMsg.getUserId()));
    }


    /**
     * 获取轮播图
     */
    @GetMapping("rotations")
    public ResponseEntity<List> getRotation(){
        List<String> imgPathes = indexService.getRotation();
//        model.addAttribute("rotations", imgPathes);
        return ResponseEntity.ok(imgPathes);
    }

    /**
     * 获取最新帖子
     */
    @GetMapping("newNotes")
    public ResponseEntity<List> getNewNotes(){
        List<NoteConstant> newNotes = indexService.getNewNotes();
        List<String> noteTitle = new ArrayList<>();
        for (NoteConstant newNote : newNotes) {
            noteTitle.add(newNote.getNoteTitle());
        }
//        model.addAttribute("newNotes", noteTitle);
        return ResponseEntity.ok(noteTitle);
    }
}
