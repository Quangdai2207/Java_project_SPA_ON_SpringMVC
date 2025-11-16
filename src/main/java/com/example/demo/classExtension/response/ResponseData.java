package com.example.demo.classExtension.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String message;
    private int status;
    private Object data;
}
