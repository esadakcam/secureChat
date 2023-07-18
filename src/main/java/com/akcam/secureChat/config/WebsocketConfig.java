package com.akcam.secureChat.config;

import lombok.NonNull;
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
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    //TODO: Examine EnableWebSocketMessageBroker and WebSocketMessageBrokerConfigurer
    private static int size = 2048*2048;
    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry
                                               registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("localhost").withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(size);
        registry.setSendBufferSizeLimit(size);
        registry.setSendTimeLimit(size);
    }

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(size);
        container.setMaxSessionIdleTimeout(Integer.toUnsignedLong(size));
        container.setAsyncSendTimeout(Integer.toUnsignedLong(size));
        container.setMaxBinaryMessageBufferSize(size);
        return container;
    }
}
