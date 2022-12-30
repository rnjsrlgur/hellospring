package com.start.hellospring.repository;

import com.start.hellospring.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICommentRepository {
    List<Comment> findAllByTableId(int id);

    Boolean saveComment(Comment comment);

}
