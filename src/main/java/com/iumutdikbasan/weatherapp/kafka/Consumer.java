package com.iumutdikbasan.weatherapp.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @KafkaListener(
            topics = "info",
            groupId = "info-group"
    )
    public void consumeInfo(String message) {
        log.info(message);
    }

    @KafkaListener(
            topics = "debug",
            groupId = "debug-group"
    )
    public void consumeDebug(String message) {
        log.debug(message);
    }

    @KafkaListener(
            topics = "error",
            groupId = "error-group"
    )
    public void consumeError(String message) {
        log.error(message);
    }

    @KafkaListener(
            topics = "warn",
            groupId = "warn-group"
    )
    public void consumeWarn(String message) {
        log.warn(message);
    }

    @KafkaListener(
            topics = "trace",
            groupId = "trace-group"
    )
    public void consumeTrace(String message) {
        log.trace(message);
    }


}