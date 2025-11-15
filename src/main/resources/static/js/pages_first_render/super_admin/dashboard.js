import superControlFeature from "./super_control_features.js";
import sectionSuperMainPage from "./feature_components/section_mainpage.js";
import sectionSuperManageAccountPage from "./feature_components/section_manage_account.js";

export const SUPER_DASHBOARD = () => {
    const super_dashboard = $("#super-admin-page")
    if (super_dashboard.length === 0) return;

    $.ajax({
        type: "GET",
        url: "/super-admin/render-super-admin-page",
        success: function (res) {
            const {metadata, email, roles} = res;
            // ** Render DOM trang quản lý Admin Dashboard từ file html
            const dashboard = "/html/dashboard/super_admin/super_admin.html"
            $.get(dashboard)
                .done((html) => {
                    super_dashboard.html(html)

                    //** Hien thi mac dinh trang chinh cua dashboard super admin
                    sectionSuperMainPage();
                    const tagName = $("span b#super-admin-username");
                    setTimeout(() => {
                        if (tagName) tagName.text(`${metadata.first_name} ${metadata.last_name}`);
                    }, 10)

                    //** Lang nghe su kien nut bam dieu khien append cac sections tinh nang cua super admin
                    setTimeout(() => {
                        superControlFeature();
                    }, 100)
                })
                .fail(() => {
                    console.error("Không thể tải file HTML:", dashboard);
                    super_dashboard.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
                })
        },
        error: function () {
            console.log("co loi trong qua trinh render DOM super dashboard");
        }
    })
}