package com.example.demo.configurations.interceptors.components;

import com.example.demo.helpers.ipDevices.IpDeviceModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class IpInterceptorComponents implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipDevice = request.getLocalAddr(); // Lay IP nguoi dung
        String userAgent = request.getHeader("User-Agent"); // lay trinh duyet nguoi dung dang su dung
        String Session = request.getSession().getId(); // lay session nguoi dung

        IpDeviceModel  ipDeviceModel = new IpDeviceModel(ipDevice, userAgent, Session);
        System.out.println(ipDeviceModel.toString());
        return true;
    }
}
