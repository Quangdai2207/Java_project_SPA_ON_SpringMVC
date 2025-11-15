package com.example.demo.helpers.ipDevices;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class IpDeviceModel {
    String Ip;
    String userAgent;
    String sessionId;
}
