package com.mall.bit.cqt.mall.service.impl;

import com.mall.bit.cqt.mall.entity.Comment;
import com.mall.bit.cqt.mall.mapper.CommentMapper;
import com.mall.bit.cqt.mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Integer addUser(String user, String username, String password) {
        return commentMapper.addUser(user,username,password);
    }

    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }
}
