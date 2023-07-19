package com.akcam.secureChat.config;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocketMessageBroker
@Log4j2
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {
    //TODO: Examine EnableWebSocketMessageBroker and WebSocketMessageBrokerConfigurer
    private static int size = 2048*2048;
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
    @Override
    public void configureWebSocketTransport(@NonNull WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(size);
        registry.setSendBufferSizeLimit(size);
        registry.setSendTimeLimit(size);
    }
    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(32768);
        container.setMaxBinaryMessageBufferSize(32768);
        log.info("Websocket factory returned");
        return container;
    }
}
