
export default function sectionAdminDoctor() {
    var dashboardAdminMainContent = $("#admin-dashboard-main-content");
    const doctorPage = "/html/dashboard/admin/feature_components/section_manage_doctor.html"
    $.get(doctorPage)
        .done((html) => {
            dashboardAdminMainContent.empty();
            dashboardAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", doctorPage);
            dashboardAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}