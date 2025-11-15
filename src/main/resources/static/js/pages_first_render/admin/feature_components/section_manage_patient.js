export default function sectionAdminPatient() {
    var dashboardAdminMainContent = $("#admin-dashboard-main-content");
    const patientPage = "/html/dashboard/admin/feature_components/section_manage_patient.html"
    $.get(patientPage)
        .done((html) => {
            dashboardAdminMainContent.empty();
            dashboardAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", facultyPage);
            dashboardAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
