package com.start.hellospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardShowDto {
    private int board_id;
    private String subject;
    private int price;
    private byte[] image;


}
