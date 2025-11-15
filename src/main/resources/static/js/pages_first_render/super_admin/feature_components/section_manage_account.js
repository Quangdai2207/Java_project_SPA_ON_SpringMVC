export default function sectionSuperManageAccountPage() {
    var superAdminMainContent = $("#super-admin-dashboard-main-content");
    const superManageAccountPage = "/html/dashboard/super_admin/feature_components/section_manage_account.html"
    $.get(superManageAccountPage)
        .done((html) => {
            superAdminMainContent.empty()
            superAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", superManageAccountPage);
            superAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
