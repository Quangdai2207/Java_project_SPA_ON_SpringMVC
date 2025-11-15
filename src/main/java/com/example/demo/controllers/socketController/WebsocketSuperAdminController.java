package com.example.demo.controllers.socketController;

import org.flywaydb.core.internal.util.JsonUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <hr/>
 * <h1 style="color: white">WebSocket Controller</h1>
 * <p>
 *     <ol>
 *         <li>
 *             Là nơi nhận và điều phối dữ liệu từ người dùng liên tục trong quá trình kết nối Websocket
 *         </li>
 *         <li>
 *             Trong WebSocket Controller, có hai annotation quan trọng cần ghi nhớ:
 *         </li>
 *         <div style="margin-left: 30px">
 *              <ol>
 *                  <li><b>@MessageMapping("/route")</b> – nhận message từ client gửi lên (chiều lên - upstream).</li>
 *                  <li><b>@SendTo("/route")</b> – gửi message ngược lại cho client (chiều xuống - downstream).</li>
 *              </ol>
 *         </div>
 *     </ol>
 * </p>
 * <br />
 * <h3 style="color: white">Giải thích chi tiết</h3>
 * <div style="margin-left: 20px">
 *  <h4>@MessageMapping</h4>
 *  <p style="margin-left: 20px;">
 *       Khi client gửi dữ liệu tới đích có tiền tố <code>/app/...</code>,
 *       Spring sẽ định tuyến (route) message đó tới phương thức
 *       được đánh dấu bằng <code>@MessageMapping</code>.<br/>
 *       Đây là chiều dữ liệu từ <b>Client → Server</b>.
 *  </p>
 * </div>
 * <div style="margin-left: 20px">
 *  <h4>@SendTo</h4>
 *  <p style="margin-left: 20px;">
 *       Sau khi phương thức xử lý xong, giá trị trả về sẽ được gửi tới tất cả
 *       client đã <code>subscribe</code> vào topic tương ứng.<br/>
 *       Đây là chiều dữ liệu từ <b>Server → Client</b>.
 *  </p>
 * </div>
 * <br/>
 * <h3 style="color: white">Ví dụ minh họa</h3>
 * <pre>
 * &#64;MessageMapping("/chat.sendMessage")
 * &#64;SendTo("/topic/public")
 * public ChatMessage sendMessage(ChatMessage message) {
 *     return message;
 * }
 * </pre>
 *
 * <p style="margin-left: 20px">
 *     - Client gửi tới: <code>/app/chat.sendMessage</code><br/>
 *     - Server gửi lại tới: <code>/topic/public</code><br/>
 *     - Các client đã subscribe <code>/topic/public</code> sẽ nhận được message ngay.
 * </p>
 * <br/>
 * <div>
 *     <h3 style="font-weight: 900; color: white">Lưu ý:</h3>
 *     <div style="margin-left: 20px; color: white">
 *         <ul>
 *             <li>
 *                 UI Client hay bất kỳ page client nào muốn nhận kết quả từ annotaion @SenTo() thì phải đăng
 *                 ký nhận kết quả xử lý bằng subscribe().
 *             </li>
 *         </ul>
 *     </div>
 * </div>
 * <hr/>
 */

@Controller
@RequestMapping
public class WebsocketSuperAdminController {
    //** Nhận và phản hồi thông báo Login thành công từ Client
    @MessageMapping("/super-admin/upstream/notifications/login") // ** Luong upstream danh cho super admin => /app/super-admin
    @SendTo("/topic/super-admin/notification") // ** Luong DownnStream phan hoi cho topic super admin
    public Map<String, String> alertLogin(Map<String, Object> message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat = sdf.format(new Date());

        String email = (String) message.get("email");
        String role = (String) message.get("role");
        String content = (String) message.get("content");
        String timestamp = (String) message.get("timestamp");
        System.out.println("------------------------------------");
        System.out.println(
                "+ Email: " + email + "\n" +
                "+ Role: " + role + "\n" +
                "+ Content:" + content + "\n" +
                "+ timestamp: " + dateFormat.formatted(new Date())
        );
        System.out.println("------------------------------------");
        Map<String, String> res = new HashMap<>();
        res.put("type", "login");
        res.put("message", dateFormat + " - Account [" + email + "] Đăng nhập thành công.");
        return res;
    }

    @SendTo("/topic/interceptor") // ** Luong DownnStream phan hoi cho topic super admin
    public Map<String, Object> interceptor(Map<String, Object> message) {
        return new HashMap<>() {
            {
                put("interceptor", message );
            }
        };
    }

    //** Nhận va phan hoi thông bao Logout thành công từ người dùng
    @MessageMapping("/super-admin/upstream/notifications/logout") // ** Luong upstream danh cho super admin => /app/super-admin
    @SendTo("/topic/super-admin/notification") // ** Luong DownnStream phan hoi cho topic super-admin
    public Map<String, String> alertLogout(Map<String, Object> message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat = sdf.format(new Date());

        String email = (String) message.get("email");
        String content = (String) message.get("content");
        String timestamp = (String) message.get("timestamp");
        System.out.println("------------------------------------");
        System.out.println(
                "+ Email: " + email + "\n" +
                "+ Content:" + content + "\n" +
                "+ timestamp: " + dateFormat.formatted(new Date())
        );
        System.out.println("------------------------------------");
        Map<String, String> res = new HashMap<>();
        res.put("type", "logout");
        res.put("message", dateFormat + " - Account [" + email + "] đã đăng xuất ứng dụng");
        return res;
    }

    //** Nhận và phản hồi thông bán tài khoản đăng ký thành công từ người dùng mới
    @MessageMapping("/super-admin/upstream/notifications/register") // ** Luong upstream danh cho super admin => /app/super-admin
    @SendTo("/topic/super-admin/notification") // ** Luong DownnStream phan hoi cho topic super-admin
    public Map<String, String> alertRegister(Map<?,?> message) {
        Map<String, String> res = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat = sdf.format(new Date());

        String email = (String) message.get("email");

        System.out.println("------------------------------------");
        System.out.println(
                "+ New account: " + email + "\n" +
                "+ timestamp: " + dateFormat.formatted(new Date())
        );
        System.out.println("------------------------------------");
        res.put("type", "register");
        res.put("message", dateFormat + " - Một Tài khoản [" + email + "] vừa đăng ký thành công");
        return res;
    }
}


















































