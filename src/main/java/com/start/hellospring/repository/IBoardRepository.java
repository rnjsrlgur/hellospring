package com.start.hellospring.repository;

import com.start.hellospring.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBoardRepository {
    List<Board> findAll();
    Board findById(int id);
    Boolean modifyBoard(Board board);
    Boolean deleteBoard(int id);
    Board findRecent();

    Boolean save(Board board);


}
