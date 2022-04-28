package com.bithumb.msacommunity.service;

import com.bithumb.msacommunity.domain.Board;
import com.bithumb.msacommunity.repository.BoardRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Objects;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시글 저장
    @Override
    public Mono<Board> saveBoard(Board board) {
        return this.boardRepository.save(board);
    }

    //게시글 숨김
    @Override
    public Mono<Board> hideBoard(Integer articleId) {
        return boardRepository.findById(articleId)
                .filter(Objects::nonNull) //있는 댓번일때
                //.filter(item -> item.getVisibleyn()==0) //show상태일떄
                .doOnNext(item -> item.setVisibleyn(1))
                .flatMap(item -> boardRepository.save(item))
                .log();

    }

}
