export default function sectionAdminMainPage() {
    var dashboardAdminMainContent = $("#admin-dashboard-main-content");
    const adminMainPage = "/html/dashboard/admin/feature_components/section_mainpage.html"
    $.get(adminMainPage)
        .done((html) => {
            dashboardAdminMainContent.empty();
            dashboardAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", adminMainPage);
            dashboardAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
