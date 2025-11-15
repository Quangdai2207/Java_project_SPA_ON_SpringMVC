export default function sectionReport() {
    var portalMainContain = $("#doctor-portal-main-content");
    const reportPage = "/html/portal/doctor/feature_components/report.html"
    $.get(reportPage)
        .done((html) => {
            portalMainContain.empty();
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", reportPage);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}