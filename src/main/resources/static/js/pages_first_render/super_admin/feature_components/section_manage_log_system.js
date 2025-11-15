export default function sectionSuperManageLoglPage() {
    var superAdminMainContent = $("#super-admin-dashboard-main-content");
    const superManageLogPage = "/html/dashboard/super_admin/feature_components/section_manage_log_system.html"
    $.get(superManageLogPage)
        .done((html) => {
            superAdminMainContent.empty()
            superAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", superManageLogPage);
            superAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
