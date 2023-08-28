package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;

    public MemoResponseDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents = contents;
    }

    public MemoResponseDto(Memo memo) {
    }

//    public MemoResponseDto(Memo memo) {
//    }
}