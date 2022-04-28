package com.bithumb.msacommunity.service;

import com.bithumb.msacommunity.domain.Reply;
import com.bithumb.msacommunity.repository.ReplyRepository;
import com.bithumb.msacommunity.webclient.CommunityWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    CommunityWebClient client;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public ReplyServiceImpl(ReplyRepository replyRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.replyRepository = replyRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    //댓글 저장
    @Override
    public Mono<Reply> saveReply(Reply reply) {
        return this.replyRepository.save(reply);
    }

    //프로듀서 역
    //댓글 숨김
    public Mono hideReply(Integer replyId) {
        String message = ">>>>>>>>>>>>>>>>kafka message: deleted replyId=" + replyId;
        log.info(message);
        this.kafkaTemplate.send("myMessage", message);

        return replyRepository.findById(replyId)
                .switchIfEmpty(Mono.error(new RuntimeException(">>>>not found exception")))
                .log()
                .doOnNext(item -> item.setReplyvisibleyn(1))
                .flatMap(item -> replyRepository.save(item))
                .log()
                .onErrorResume(tr -> {
                    return Mono.just(new Reply(-1, -1, -1, "", -1, LocalDateTime.now(), LocalDateTime.now()));
                });
    }

}
