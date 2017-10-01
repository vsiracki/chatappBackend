package com.chatapp.configuration;

import com.chatapp.security.JwtTokenUtil;
import com.chatapp.services.AnnounceUserStatus;
import com.chatapp.services.UsersService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Autowired
    JwtTokenUtil tokenUtil;
    
    
//    UsersService usersService;
    
    AnnounceUserStatus announceUserStatus;
 
    @Autowired
    public void setAnnounceUserStatus(AnnounceUserStatus announceUserStatus) {
        this.announceUserStatus = announceUserStatus;
    }

    
//    
//    @Autowired
//    public void setUsersService(UsersService usersService) {
//        this.usersService = usersService;
//    }
//    
    

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setUserDestinationPrefix("/user");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/messages").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new ChannelInterceptorAdapter() {

            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {

                StompHeaderAccessor accessor
                        = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = accessor.getNativeHeader("token").get(0);

                   
                    String userName = tokenUtil.getUsernameFromToken(token);
                    announceUserStatus.isOnline(userName);
                    System.out.println(userName+" is connected");
                    
                    if (userName != null) {
                        Principal user = new Principal() {
                            @Override
                            public String getName() {
                                return userName;
                            }
                        }; // access authentication header(s)
                        accessor.setUser(user);
                    }
                }
                if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                    System.out.println(accessor.getUser().getName()+" is disconnected");
                    
                    announceUserStatus.isOffline(accessor.getUser().getName());
                }
                

                return message;
            }
        });
    }
}
