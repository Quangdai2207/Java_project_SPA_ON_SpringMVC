export default function sectionPrescription() {
    var portalMainContain = $("#doctor-portal-main-content");
    const prescriptionPage = "/html/portal/doctor/feature_components/prescription.html"
    $.get(prescriptionPage)
        .done((html) => {
            portalMainContain.empty();
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", prescriptionPage);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}