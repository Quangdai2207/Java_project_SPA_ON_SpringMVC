import sectionMainPage from "./feature_components/section_mainpage.js";
import patientControlFeature from "./control_feature_script.js";
export const PATIENT_PORTAL = () => {
    const patient_portal = $("#patient-page")
    if (patient_portal.length === 0) return;

    $.ajax({
        type: "GET",
        url: "/patient/render-patient-page",
        success: function (res) {
            const {metadata, email} = res;
            // ** Render DOM section id=patient-portal, section chung cua patient-page
            $.get("/html/portal/patient/patient.html")
                .done((html) => {
                    patient_portal.html(html)
                    if ($("#patient-portal")) $("#tag-username").text(`${metadata.first_name} ${metadata.last_name}`);
                    //** Append sections trang chủ của patient-portal
                    sectionMainPage();
                    setTimeout(() => {
                        patientControlFeature();
                    }, 100)
                })
                .fail(() => {
                    console.error("Không thể tải file HTML:", "/html/portal/patient/patient.html");
                    patient_portal.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
                })
        },
        error: function () {
            console.log("co loi trong qua trinh render DOM patient portal");
        }
    })
}