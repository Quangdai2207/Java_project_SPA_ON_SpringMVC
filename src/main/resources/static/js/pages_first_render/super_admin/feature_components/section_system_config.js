export default function sectionSuperSystemConfigPage() {
    var superAdminMainContent = $("#super-admin-dashboard-main-content");
    const superSystemConfigPage = "/html/dashboard/super_admin/feature_components/section_system_config.html"
    $.get(superSystemConfigPage)
        .done((html) => {
            superAdminMainContent.empty()
            superAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", superSystemConfigPage);
            superAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
