package org.zerock.b01.service;

import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {

    default Board dtoToEntity(BoardDTO boardDTO) {

        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();

        if (boardDTO.getFileNames() != null) {
            boardDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                for(int i = 0; i< arr.length; i++){
                    System.out.println("arr 테스트 " + arr[i]);
                }
                board.addImage(arr[0], arr[1]);
            });
        }
        return board;
    }

    default BoardDTO entityToDto(Board board) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .modDate(board.getModDate())
                .regDate(board.getRegDate())
                .build();

        List<String> fileNames = board.getImageSet()
                .stream()
                .sorted()
                .map(boardImg -> boardImg.getUuid() + "_" + boardImg.getFileName())
                .collect(Collectors.toList());

        boardDTO.setFileNames(fileNames);

        return boardDTO;
    }

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    //댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

}