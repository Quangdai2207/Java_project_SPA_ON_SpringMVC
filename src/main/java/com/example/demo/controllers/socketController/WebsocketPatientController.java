package com.example.demo.controllers.socketController;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class WebsocketPatientController {

    @MessageMapping("/patient/upstream") // ** Luong upstream danh cho patient => /app/patient
    @SendTo("/topic/patient/notification") // ** Luong DownnStream phan hoi cho topic patient
    public String alert(Object message) {
        System.out.println("Message duoc gui tu Client => " + message.toString());
        return message.toString();
    }

}


















































