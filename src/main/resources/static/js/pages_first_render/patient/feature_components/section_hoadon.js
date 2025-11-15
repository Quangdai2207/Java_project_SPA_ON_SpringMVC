export default function sectionHoaDon() {
    var portalMainContain = $("#patient-portal-main-content");
    const sectionHoaDon = "/html/portal/patient/feature_components/section_hoadon.html"
    $.get(sectionHoaDon)
        .done((html) => {
            portalMainContain.empty()
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", sectionHoaDon);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}