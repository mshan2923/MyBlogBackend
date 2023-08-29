package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
    // RequestDto -> Entity
        Board board = new Board(requestDto);
    // DB 저장
        Board saveBoard = boardRepository.save(board);
    // Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);
        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoards() {
    // DB 조회
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public List<BoardResponseDto> getBoardsByKeyword(String keyword) {
        return boardRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(BoardResponseDto::new).toList();
    }

    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
// 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);
// memo 내용 수정
        board.update(requestDto);
        return id;
    }

    public Long deleteBoard(Long id) {
// 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);
// memo 삭제
        boardRepository.delete(board);
        return id;
    }

    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}