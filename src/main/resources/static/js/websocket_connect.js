//** Lay Role nguoi dung the the Meta Thymeleaf
const metaRole = $("meta[name='role']").attr("content") || "anonymous"
const userRoleFormated = metaRole
    .replace("ROLE_", "")
    .replace(/_+/, "-")
    .trim()
    .toLowerCase();

//** KHai bao StompClient:
let stompClient = null;

//** Function ket noi WS
function connectWS(userRole) {
    //** Khai bao doi tuong SockJS()
    const socket = new SockJS("/ws");

    //** ket noi StomClient WS:
    stompClient = Stomp.over(socket);
    if (!stompClient) {
        console.error("StompClient chưa được kết nối >>>")
        return;
    }
    console.log("Đang kết nối Websocket >>>");
    stompClient.connect({}, (frame) => {
        console.log("Frame => ", frame);
        console.log("Kết nối WS đang được duy trì <<<")
        //?? Lay thong tin username ket noi WS tu frame object voi thuoc tinh headers sau khi ket noi thanh cong
        const username = frame.headers["user-name"] || null;
        //** Ket noi Topic WS cho tat ca cac ROLE
        const topic = `/topic/${userRole}/notification`;
        //** Dang ky nhan phan hoi tu server theo ROLE:
        switch (topic) {
            case "/topic/super-admin/notification":
                stompClient.subscribe(topic, (res) => {
                    accountStatus(res);
                });
                break;
            case "/topic/admin/notification":
                break;
            case "/topic/doctor/notification":
                break;
            case "/topic/patient/notification":
                break;
            default:
                return;
        }

        //** Sau khi client ket noi thanh cong thi gui thong tin truy cap vao he thong ve cho server the route cua super-admin quan ly dang nhap cua account
        const params = new URLSearchParams(window.location.search);
        console.log("Params =>", params.get("logged"));
        const actions = params.get("logged");
        var message = {
            email: username,
            role: userRole,
            content: username === null ? "Có sự cố khi đăng nhập từ người dùng!" : `${username} Đăng nhập thành công!`,
            timestamp: new Date().toISOString()
        }
        //** Lay query khi dang nhap thanh cong
        if (actions && actions === "success") {
            stompClient.send("/app/super-admin/upstream/notifications/login", {}, JSON.stringify(message));
        }
    });

    handleFormRegister(stompClient);

    //** Gui thong bao khi co tai khoan thoat ung dung
    $(`#btn-logout-${userRole}`).on("click", function () {
        const email = $(this).data("account")
        const message = {
            email: email,
            content: `Tai khoan ${email} - Đã đăng xuất ứng dụng.`,
            timestamp: new Date().toISOString()
        }
        stompClient.send("/app/super-admin/upstream/notifications/logout", {}, JSON.stringify(message));
    });
    console.log("Kết nối thành công >>>");
}

connectWS(userRoleFormated)

function accountStatus(res) {
    const log = JSON.parse(res.body)
    const superTagLogAlert = $("#super-admin-mainpage-log-alerts")
    setTimeout(() => {
        switch (log.type) {
            case "login":
                superTagLogAlert.append(`
                     <div><span class="text-green-300 font-bold">[Login] - </span>${log.message}</div>
                `)
                break;
            case "register":
                superTagLogAlert.append(`
                     <div><span class="text-lime-300 font-bold">[Register] - </span>${log.message}</div>
                `)
                break;
            case "logout":
                superTagLogAlert.append(`
                     <div><span class="text-orange-500 font-bold">[Logout] - </span>${log.message}</div>
                `)
                break;
        }
    }, 10)
}


function handleFormRegister(stomp) {
    $("#form-register").on("submit", function (e) {
        e.preventDefault();

        const formData = $(this).serializeArray();
        const data = {};
        formData.forEach(item => {
            data[item.name] = item.value;
        })

        const $errorDiv = $("#register-error");

        console.log(data);
        $.ajax({
            type: "POST",
            url: "/api/v1/account/register",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (res) {
                $errorDiv.empty();
                const message = {email : res.email}
                stomp.send("/app/super-admin/upstream/notifications/register", {}, JSON.stringify(message));
                setTimeout(() => {
                    window.location.href = "/auth/login"
                },100)
            },
            error: function (xhr) {
                if (xhr.responseJSON.success === false) {
                    const errors = xhr.responseJSON.errors;
                    if (typeof errors === "object") {
                        for (const key in errors) {
                            $errorDiv.empty();
                            $errorDiv.removeClass("hidden")
                            $errorDiv.append(
                                `<ul>
                                    <li>➤ ${errors[key]}</li>
                                </ul>`
                            )
                        }
                    }
                }
            }
        });
    })
}



















