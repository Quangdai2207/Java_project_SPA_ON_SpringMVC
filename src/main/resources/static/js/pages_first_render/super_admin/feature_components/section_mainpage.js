export default function sectionSuperMainPage() {
    var superAdminMainContent = $("#super-admin-dashboard-main-content");
    const superMainPage = "/html/dashboard/super_admin/feature_components/section_mainpage.html"
    $.get(superMainPage)
        .done((html) => {
            superAdminMainContent.empty()
            superAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", superMainPage);
            superAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
