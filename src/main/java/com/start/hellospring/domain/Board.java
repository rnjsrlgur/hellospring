package com.start.hellospring.domain;

import com.start.hellospring.dto.BoardSaveDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Board {
    private Long board_id;
    private String subject;
    private int price;
    private String image_name;

    public static Board getBoard(BoardSaveDto boardSaveDto) {
        return new Board(null, boardSaveDto.getName(), boardSaveDto.getPrice(), null);
    }
}
