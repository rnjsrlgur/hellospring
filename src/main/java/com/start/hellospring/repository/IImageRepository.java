package com.start.hellospring.repository;

import com.start.hellospring.domain.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IImageRepository {

    Image get(int table_id);
    Boolean save(Image image);


}
