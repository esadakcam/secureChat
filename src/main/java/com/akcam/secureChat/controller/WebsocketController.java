package com.akcam.secureChat.controller;

import com.akcam.secureChat.application.WebsocketService;
import com.akcam.secureChat.domain.message.FetchMessage;
import com.akcam.secureChat.domain.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
@RequiredArgsConstructor(onConstructor_= {@Autowired})
public class WebsocketController {
    private final WebsocketService websocketService;

    @MessageMapping("/messageTopic")
    public void message(@Payload Message message){
        websocketService.handleMessage(message);
    }

    @MessageMapping("/fetch")
    public void fetch(@Payload FetchMessage message){
        websocketService.handleMessage(message);
    }
}
