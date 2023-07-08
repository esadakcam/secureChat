package com.akcam.secureChat.config;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry
                                               registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("localhost").withSockJS();
    }

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config){
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic/", "/user/");
        config.setUserDestinationPrefix("/user");
    }
    //TODO: add message coneverter public boolean configureMessageConverters(List<MessageConverter> messageConverters)
}
