export default function sectionMyPatient() {
    var portalMainContain = $("#doctor-portal-main-content");
    const sectionMyPatient = "/html/portal/doctor/feature_components/my_patient.html"
    $.get(sectionMyPatient)
        .done((html) => {
            portalMainContain.empty();
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", sectionMyPatient);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}