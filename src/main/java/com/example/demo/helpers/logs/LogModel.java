package com.example.demo.helpers.logs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LogModel {
    private String IP;
    private String URL;
    private String HEADER;
    private String URI;
    private String DATE;
}
