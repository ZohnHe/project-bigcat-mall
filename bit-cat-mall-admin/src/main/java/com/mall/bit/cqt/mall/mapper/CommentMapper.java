package com.mall.bit.cqt.mall.mapper;


import com.mall.bit.cqt.mall.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    Integer addUser(@Param("user") String user, @Param("username") String username, @Param("password") String password);

    List<Comment> selectAll();


}
