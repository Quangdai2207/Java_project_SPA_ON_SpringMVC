export default function sectionSuperManageDoctorPage() {
    var superAdminMainContent = $("#super-admin-dashboard-main-content");
    const superManageDoctorPage = "/html/dashboard/super_admin/feature_components/section_manage_doctor.html"
    $.get(superManageDoctorPage)
        .done((html) => {
            superAdminMainContent.empty()
            superAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", superManageDoctorPage);
            superAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
