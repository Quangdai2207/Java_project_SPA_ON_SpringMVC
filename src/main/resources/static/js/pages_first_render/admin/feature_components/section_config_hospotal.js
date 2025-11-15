export default function sectionAdminHospital() {
    var dashboardAdminMainContent = $("#admin-dashboard-main-content");
    const configHospitalPage = "/html/dashboard/admin/feature_components/section_config_hospital.html"
    $.get(configHospitalPage)
        .done((html) => {
            dashboardAdminMainContent.empty();
            dashboardAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", configHospitalPage);
            dashboardAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
