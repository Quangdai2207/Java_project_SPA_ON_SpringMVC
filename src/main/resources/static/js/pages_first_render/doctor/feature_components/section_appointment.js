export default function sectionAppointment() {
    var portalMainContain = $("#doctor-portal-main-content");
    const appointmentPage = "/html/portal/doctor/feature_components/appointment.html"
    $.get(appointmentPage)
        .done((html) => {
            portalMainContain.empty();
            portalMainContain.append(html)
        })
        .fail(() => {
            console.error("Khong the load file html:", appointmentPage);
            portalMainContain.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}