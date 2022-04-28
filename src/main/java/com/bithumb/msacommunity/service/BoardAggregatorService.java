package com.bithumb.msacommunity.service;

import com.bithumb.msacommunity.domain.Board;
import com.bithumb.msacommunity.domain.BoardAggregate;
import com.bithumb.msacommunity.domain.Reply;
import com.bithumb.msacommunity.webclient.BoardClient;
import com.bithumb.msacommunity.webclient.ReplyClient;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardAggregatorService {
    private final BoardClient boardClient;
    private final ReplyClient replyClient;

    //컨수머 역
    public Mono<BoardAggregate> getBoard(Integer boardId) {
        return Mono.zip(this.boardClient.getBoard(boardId), this.replyClient.getReplyList(boardId)).map(this::combine);
    }

    private BoardAggregate combine(Tuple2<Board, List<Reply>> tuple) {
        return BoardAggregate.create(tuple.getT1(), tuple.getT2());
    }



}
