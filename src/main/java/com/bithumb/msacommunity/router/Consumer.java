package com.bithumb.msacommunity.router;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
@Slf4j
public class Consumer {
    //private final Logger logger = (Logger) LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "myMessage", groupId = "my-group")
    public void consume(String message) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", message));
    }
}
