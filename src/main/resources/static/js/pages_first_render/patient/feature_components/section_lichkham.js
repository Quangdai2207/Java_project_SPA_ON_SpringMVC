export default function sectionLichKham() {
    var portalMainContent = $("#patient-portal-main-content");
    const sectionLichKham = "/html/portal/patient/feature_components/section_lichkham.html"
    $.get(sectionLichKham)
        .done((html) => {
            portalMainContent.empty()
            portalMainContent.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", sectionLichKham);
            portalMainContent.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}