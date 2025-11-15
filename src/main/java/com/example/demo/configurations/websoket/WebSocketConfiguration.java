package com.example.demo.configurations.websoket;

import com.example.demo.configurations.websoket.compnents.WebsocketPayloadInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private WebsocketPayloadInterceptor  websocketPayloadInterceptor;

    //?? Dang ky nhan du lieu tu Interceptor
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(websocketPayloadInterceptor);
    }

    //?? Khai bao duong dan tham gia vao websocket nhan du lieu tu Client
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //** Cho phep duong dan bat dau bang /ws
        //** Hoac khi deploy ung dung len host thi /ws co the thay bang duong dan thuc te nhu sau:
        //** =>> https://domains/ws
        registry.addEndpoint("/ws") // endpoint mà client dùng SockJS('/ws')
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        messageConverters.add(new MappingJackson2MessageConverter());
        return true; // true nếu muốn sử dụng converters tự thêm và bỏ mặc định
    }

    // Phan phoi tin nhan theo dang nhom hay ca nhan 1 - 1
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");
        /* Thong bao cho broker biet ung dung nao se nhan duoc tin nhan */
        registry.setApplicationDestinationPrefixes("/app");
    }
}
