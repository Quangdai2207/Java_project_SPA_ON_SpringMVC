export default function sectionSuperManageHospitalPage() {
    var superAdminMainContent = $("#super-admin-dashboard-main-content");
    const superManageHospitalPage = "/html/dashboard/super_admin/feature_components/section_manage_hospital.html"
    $.get(superManageHospitalPage)
        .done((html) => {
            superAdminMainContent.empty()
            superAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", superManageHospitalPage);
            superAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
