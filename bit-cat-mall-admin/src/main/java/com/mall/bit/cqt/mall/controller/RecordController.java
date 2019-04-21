package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.entity.Record;
import com.mall.bit.cqt.mall.entity.User;
import com.mall.bit.cqt.mall.service.RecordService;
import com.mall.bit.cqt.mall.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(tags = "聊天室模块")
@RequestMapping("record")
public class RecordController {

    @Resource
    RecordService recordService;

    @Resource
    UserService userService;

    //跳转聊天界面
    @GetMapping("/char")
    public String login(String receiverId,HttpServletRequest request){
        Record record = new Record();
        User user = (User) request.getSession().getAttribute("user");
        //接受者id
        record.setReceiverId(Integer.parseInt(receiverId));
        //接受者头像
        User users = userService.getById(Long.valueOf(receiverId));
        record.setReceiverImage(users.getImage());
        //发送者id
        record.setSenderId(Math.toIntExact(user.getId()));
        //发送者头像
        record.setSenderImage(user.getImage());
        request.getSession().setAttribute("record",record);
        return "/chars";
    }

    //获取发送者接受者对象信息
    @ResponseBody
    @PostMapping(value = "getMsg")
    public Record getMsg(HttpServletRequest request){
        Record record;
        record  = (Record) request.getSession().getAttribute("record");
        return record;
    }
    //保存聊天记录
    @ResponseBody
    @PostMapping(value = "/save")
    public void save(String senderId,String recevierId ,String message){
        Record record = new Record();
        record.setSenderId(Integer.parseInt(senderId));
        record.setReceiverId(Integer.parseInt(recevierId));
        record.setMessage(message);
        recordService.save(record);
    }
    //查找聊天记录
    @ResponseBody
    @PostMapping(value = "/list")
    public List<Record> records(String sender_id, String recevier_id){
        Record record = new Record();
        record.setSenderId(Integer.parseInt(sender_id));
        record.setReceiverId(Integer.parseInt(recevier_id));
        List<Record> recordList = recordService.getRecordList(record);
        return recordList;
    }

}
