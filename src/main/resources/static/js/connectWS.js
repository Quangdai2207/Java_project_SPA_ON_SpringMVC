var stompClient = null;

var userRole = $('meta[name="role"]').attr('content');
userRole = userRole
    .replace("ROLE_", "")
    .replace(/_+/g, "-")
    .toLowerCase();

function connectToWS(role) {
    const socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);
    stompClient.debug = null; // tắt log debug STOMP
    stompClient.connect({}, frame => {
        // Subscribe topic riêng của role
        const topic = `/topic/${role}/notification`;
        stompClient.subscribe(topic, message => {
            console.log(`Message nhận từ WS (${topic}):`, message);
        });

        // Chỉ super-admin gửi login/logout notification
        if (role === "super-admin") {
            notifyLoginLogout(role);
        }
    }, error => {
        console.error(`Lỗi kết nối WS cho role ${role}:`, error);
    });
}

function notifyLoginLogout(role) {
    if (!stompClient || !stompClient.connected) return;

    const isAnonymous = (role === "anonymous" || role === "unauthorize");
    const message = {
        sender: role,
        content: isAnonymous ? "Logout" : "Đăng nhập thành công",
        timestamp: new Date().toISOString()
    };
    const route = isAnonymous ? `/app/${role}/upstream/logout` : `/app/${role}/upstream/login`;

    stompClient.send(route, {}, JSON.stringify(message));
    console.log(`Gửi thông báo ${message.content} tới WS cho role ${role}`);
}

function sendNotification(role, content) {
    if (!stompClient || !stompClient.connected) return;

    const message = {
        sender: role,
        content: content,
        timestamp: new Date().toISOString()
    };

    stompClient.send(`/app/${role}/upstream/notification`, {}, JSON.stringify(message));
    console.log(`Gửi notification từ role ${role}:`, content);
}

connectToWS(userRole);

// Ví dụ test gửi notification cho patient
// if(userRole === "patient") sendNotification(userRole, "Test notification patient");
