export default function sectionMainPage() {
    var portalMainContain = $("#doctor-portal-main-content");
    const mainPage = "/html/portal/doctor/feature_components/section_mainpage.html"
    $.get(mainPage)
        .done((html) => {
            portalMainContain.empty();
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", mainPage);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}