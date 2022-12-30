package com.start.hellospring.controller;

import com.start.hellospring.domain.Comment;
import com.start.hellospring.repository.ICommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final ICommentRepository commentRepository;
    @PostMapping("/get")
    public ResponseEntity<List<Comment>> getCommmentList(@RequestBody HashMap<String, Object> map) {
        Integer id = (Integer) map.get("table_id");
        List<Comment> commentList = commentRepository.findAllByTableId(id);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Comment comment) {
        commentRepository.saveComment(comment);
        return ResponseEntity.ok().build();
    }

}
