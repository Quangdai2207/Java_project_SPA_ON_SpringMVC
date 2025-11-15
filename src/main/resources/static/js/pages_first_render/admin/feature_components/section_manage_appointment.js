export default function sectionAdminAppointment() {
    var dashboardAdminMainContent = $("#admin-dashboard-main-content");
    const appointmentPage = "/html/dashboard/admin/feature_components/section_manage_appointment.html"
    $.get(appointmentPage)
        .done((html) => {
            dashboardAdminMainContent.empty();
            dashboardAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", appointmentPage);
            dashboardAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}