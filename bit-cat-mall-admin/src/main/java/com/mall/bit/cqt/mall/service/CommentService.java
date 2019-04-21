package com.mall.bit.cqt.mall.service;

import com.mall.bit.cqt.mall.entity.Comment;

import java.util.List;

public interface CommentService {

    Integer addUser(String user, String username, String password);
    public List<Comment> selectAll();
}
