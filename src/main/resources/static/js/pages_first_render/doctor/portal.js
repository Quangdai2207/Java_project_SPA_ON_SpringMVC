import sectionMainPage from "./feature_components/section_mainpage.js";
import doctorControlFeature from "./doctor_control_feature.js";
export const DOCTOR_PORTAL = () => {
    const doctor_portal = $("#doctor-page")
    if (doctor_portal.length === 0) return;

    $.ajax({
        type: "GET",
        url: "/doctor/render-doctor-page",
        success: function (res) {
            if (res) {
                const {metadata, email, roles} = res;
                // ** Render DOM Doctor Portal tu trang html
                $.get("/html/portal/doctor/doctor.html")
                    .done((html) => {
                        doctor_portal.html(html)
                        $("#doctor-portal-name").text(`Dr. ${metadata.first_name} ${metadata.last_name}`)

                        //** Appends cac noi dung page cua Doctor portal:
                        sectionMainPage();

                        setTimeout(() => {
                            doctorControlFeature();
                        })
                    })
                    .fail(() => {
                        console.error("Không thể tải file HTML:", "/html/portal/doctor/doctor.html");
                        doctor_portal.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
                    })
            } else {

            }
        },
        error: function () {
            console.log("co loi trong qua trinh render DOM doctor-portal");
        }
    })
}