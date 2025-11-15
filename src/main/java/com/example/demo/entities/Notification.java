package com.example.demo.entities;

import lombok.*;

/**
 * <div style="color: white">
 *     <h1 style="color: white; text-decoration: underline"> Lưu ý:</h1>
 *     <p style="margin-left:20px">
 *          Đây là Class entity cho <strong style="text-decoration: underline">Notification</strong>,
 *          không cần khai báo trong dữ liệu DB, class enitty <strong style="text-decoration: underline">Notification</strong>
 *          phục vụ cho mục địch chuẩn hoá dữ liệu đầu ra phản hồi thông báo từ WebSocket Controller.
 *     </p>
 * </div>
 *
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private String title;
    private String content;
    private String date;
}
