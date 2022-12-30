package com.start.hellospring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Image {
    private Long image_id;
    private String uploaded_name;
    private String stored_name;
    private Long table_id;

    public static Image getImage(String uploaded_name, String stored_name, Long table_id) {
        return new Image(null, uploaded_name, stored_name, table_id);
    }
}
