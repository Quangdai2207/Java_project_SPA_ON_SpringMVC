export default function sectionMainPage() {
    var portalMainContain = $("#patient-portal-main-content");
    const htmlMainPage = "/html/portal/patient/feature_components/section_mainpage.html"
    $.get(htmlMainPage)
        .done((html) => {
            portalMainContain.empty()
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", htmlMainPage);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}