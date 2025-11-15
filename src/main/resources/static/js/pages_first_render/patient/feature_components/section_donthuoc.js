export default function sectionDonThuoc() {
    var portalMainContain = $("#patient-portal-main-content");
    const sectionDonThuoc = "/html/portal/patient/feature_components/section_donthuoc.html"
    $.get(sectionDonThuoc)
        .done((html) => {
            portalMainContain.empty()
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", sectionDonThuoc);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}