
export default function sectionAdminFaculty() {
    var dashboardAdminMainContent = $("#admin-dashboard-main-content");
    const facultyPage = "/html/dashboard/admin/feature_components/section_manage_faculty.html"
    $.get(facultyPage)
        .done((html) => {
            dashboardAdminMainContent.empty();
            dashboardAdminMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", facultyPage);
            dashboardAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
