export default function sectionMedicalRecord() {
    var portalMainContain = $("#doctor-portal-main-content");
    const medicalRecordPage = "/html/portal/doctor/feature_components/medical_record.html"
    $.get(medicalRecordPage)
        .done((html) => {
            portalMainContain.empty();
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", medicalRecordPage);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}