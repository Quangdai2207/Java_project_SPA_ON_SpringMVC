import adminControlFeature from "./admin_control_feature.js";
import sectionAdminMainPage from "./feature_components/section_admin_mainpage.js";

export const ADMIN_DASHBOARD = () => {
    const admin_dashboard = $("#admin-page")
    if (admin_dashboard.length === 0) return;

    $.ajax({
        type: "GET",
        url: "/admin/render-admin-page",
        success: function (res) {
            const {metadata, email, roles} = res;
            // ** Render DOM trang quản lý Admin Dashboard từ file html
            const dashboard = "/html/dashboard/admin/admin.html";
            $.get(dashboard)
                .done((html) => {
                    admin_dashboard.html(html)
                    sectionAdminMainPage();
                    setTimeout(() => {
                        adminControlFeature();
                    }, 100)

                })
                .fail(() => {
                    console.error("Không thể tải file HTML:", dashboard);
                    admin_dashboard.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
                })
        },
        error: function () {
            console.log("co loi trong qua trinh render DOM admin dashboard");
        }
    })
}