package com.example.demo.configurations.interceptors.components;

import com.example.demo.helpers.logs.LogModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LogInterceptorComponent implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipDevice = request.getLocalAddr(); // lay dia chi IP thiet bi
        String URL = request.getRequestURL().toString(); // Lay URL nguoi dung request
        String header = request.getHeader("User-Agent"); // Kiem tra header nguoi dung request
        String URI = request.getRequestURI().toString(); // Lay URI noi bo cua routes
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateFormat = simpleDateFormat.format(new Date());

        LogModel logModel = new LogModel(ipDevice, URL, header, URI, dateFormat);
        System.out.println(logModel.toString());
        return true;
    }
}
