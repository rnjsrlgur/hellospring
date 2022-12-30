package com.start.hellospring.domain;

import lombok.Getter;

@Getter
public class Comment {
    private Long comment_id;
    private String nickname;
    private String contents;
    private Long table_id;

}
