export const index = () => {
    const home_page = $("#home-page");
    if (home_page.length === 0) {
        return;
    }

    $.ajax({
        type: "GET",
        url: "/home/render-homepage", //** Lấy dữ liệu metadata cho các trang homes từ route BE
        success: function (res) {
            if (res.status === 200) {
                const {email, roles, metadata} = res;
                //** Clear nội dung cũ
                home_page.empty();

                // ** Render DOM kiểu trang home theo ROLE
                let htmlFile = "/html/home/guest.html";

                if (roles.includes("ROLE_SUPER_ADMIN")) htmlFile = "/html/home/super_admin.html"
                else if (roles.includes("ROLE_ADMIN")) htmlFile = "/html/home/admin.html"
                else if (roles.includes("ROLE_DOCTOR")) htmlFile = "/html/home/doctor.html"
                else if (roles.includes("ROLE_PATIENT")) htmlFile = "/html/home/patient.html"

                //** Lấy nội dung file từ các files html
                $.get(htmlFile)
                    .done((html) => {
                        setTimeout(() => {
                            home_page.html(html);
                        }, 50)
                    })
                    .fail(() => {
                        console.error("Không thể tải file HTML:", htmlFile);
                        home_page.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
                    });
            }
        },
        error: function () {
            console.log("Lỗi khi render DOM homepage");
        }
    });
};
