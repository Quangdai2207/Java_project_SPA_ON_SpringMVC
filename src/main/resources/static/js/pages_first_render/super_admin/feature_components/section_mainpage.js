export default function sectionSuperMainPage() {
    var superAdminMainContent = $("#super-admin-dashboard-main-content");
    const superMainPage = "/html/dashboard/super_admin/feature_components/section_mainpage.html"
    $.get(superMainPage)
        .done((html) => {
            console.log("Rendering UI section main-page");
            superAdminMainContent.empty()
            superAdminMainContent.append(html)
            console.log("Render UI section main dashboard >>>");
            $.ajax({
                type: "GET",
                url: "/super-admin/api/data-section-main-page",
                success: function(res) {
                    console.log(res);
                },
                error: function(ex) {
                    console.log(ex);
                }
            })
        })
        .fail(() => {
            console.error("Khong the load file html:", superMainPage);
            superAdminMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}
