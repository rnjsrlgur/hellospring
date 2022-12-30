package com.start.hellospring.controller;

import com.start.hellospring.domain.Board;
import com.start.hellospring.domain.Image;
import com.start.hellospring.dto.BoardSaveDto;
import com.start.hellospring.dto.BoardShowDto;
import com.start.hellospring.repository.IBoardRepository;
import com.start.hellospring.repository.IImageRepository;
import com.start.hellospring.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final IBoardRepository boardRepository;
    private final IImageRepository imageRepository;
    private final ImageService imageService;

    @GetMapping("/example")
    public ResponseEntity<List<Board>> getOne(HttpServletRequest req, HttpServletResponse res) {
//        String requestURI = req.getRequestURI();
//        log.info("URI [{}]", requestURI); // log 찍는법
        List<Board> boardList = boardRepository.findAll();
        return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
    }

    /**
     * @RequestBody : Map 계열 형식으로 body 안에 있는 값을 <키, 값> 형식으로 가져옴.
     */
    @PostMapping("/get-detail")
    public ResponseEntity getDetail(HttpServletRequest req, HttpServletResponse res, @RequestBody HashMap<String, Object> map) throws IOException {

        Integer id = (Integer) map.get("id");
        Board findBoard = boardRepository.findById(id);
        Image image = imageRepository.get(id);
        byte[] array = imageService.getByteArray(image);
        BoardShowDto boardShowDto = new BoardShowDto(id, findBoard.getSubject(), findBoard.getPrice(), array);
        return new ResponseEntity(boardShowDto, HttpStatus.OK);
    }

    @PostMapping("/board/modify")
    public ResponseEntity modifyBoard(@RequestBody  Board board) {
        boardRepository.modifyBoard(board);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/board/delete")
    public ResponseEntity deleteBoard(@RequestBody HashMap<String, Object> map) {
        Integer id = (Integer) map.get("id");
        boardRepository.deleteBoard(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/board/save")
    public ResponseEntity saveBoard(BoardSaveDto boardSaveDto, @RequestPart(value = "image")List<MultipartFile> multipartFiles) throws IOException {
        Board board = Board.getBoard(boardSaveDto);
        boardRepository.save(board);
        Board recent = boardRepository.findRecent();
        System.out.println(recent.getBoard_id());
        imageService.save(multipartFiles, recent.getBoard_id());
        return new ResponseEntity(HttpStatus.OK);
    }



}
