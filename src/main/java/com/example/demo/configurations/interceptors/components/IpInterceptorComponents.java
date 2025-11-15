package com.example.demo.configurations.interceptors.components;

import com.example.demo.helpers.ipDevices.IpDeviceModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class IpInterceptorComponents implements HandlerInterceptor {
    /**
     * <div style="border: 1px white solid; padding: 10px">
     *     <p style="font-family: 'Tahoma'; color: white; text-align: left;">
     *         Sử dụng đối tượng <strong style="color:#eeee; font-style: italic;">SimMessagingTemplate</strong> để gửi toàn bộ dữ liệu
     *         cho Websocket theo luồng Downstream mà không cần phải thông qua Upstream từ Client
     *         tại <strong style="font-style: italic; color:#eeee;">Interceptor Layer</strong>.
     *     </p>
     *
     *     <h5 style="color: white; text-decoration: underline; margin: 8px 0;">Follows:</h5>
     *     <ul style="color: white; font-family:'Roboto Thin'; font-style: italic; margin-left: 20px;">
     *         <li>Request ・ Interceptor ・ (SimMessagingTemplate.send) ・ Websocket downstream ・ UI subscribe ・ Render</li>
     *     </ul>
     *
     *     <p style="color: white; margin-top: 15px; text-align: left;">
     *         Để Websocket nhận được dữ liệu từ Interceptor, ta cần đăng ký Interceptor trong cấu hình
     *         <strong>WebsocketConfiguration</strong>. Trong đó:
     *     </p>
     *
     *     <ul style="margin-left: 20px; color: white;">
     *         <li>
     *             Tạo một class Component implements Interface
     *             <strong>ChannelInterceptor</strong>.
     *         </li>
     *         <li>
     *             Đăng ký class vừa tạo vào Websocket bằng cách override
     *             <strong style="color:orange;">configureClientInboundChannel(ChannelRegistration registration)</strong>.
     *         </li>
     *         <li>
     *             Xem tại Component:
     *             <strong style="color:orange;">WebsocketPayloadInterceptor</strong><br/>
     *             và cấu hình:
     *             <strong style="color:orange;">WebsocketConfiguration</strong>.
     *         </li>
     *     </ul>
     * </div>
     */

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipDevice = request.getLocalAddr(); // Lay IP nguoi dung
        String userAgent = request.getHeader("User-Agent"); // lay trinh duyet nguoi dung dang su dung
        String Session = request.getSession().getId(); // lay session nguoi dung
        String URL = request.getRequestURL().toString();

        IpDeviceModel ipDeviceModel = new IpDeviceModel(ipDevice, userAgent, Session);
        System.out.println(ipDeviceModel.toString());

        Map<String, String> payload = new HashMap<>();
        payload.put("ipDevice", ipDeviceModel.toString());
        payload.put("userAgent", userAgent);
        payload.put("Session", Session);
        payload.put("URL", URL);

        System.out.println("Payload: " + payload);

        // Gửi dữ liệu xuống cho WebSocket, websocket truyền dữ liệu từ interceptor theo luồng Downstream ra
        // ngoài Client subsribe  .
        messagingTemplate.convertAndSend("/topic/super-admin/notification", payload);
        return true;
    }
}
