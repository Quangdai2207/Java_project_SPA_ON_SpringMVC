export default function contact_patient_portal() {
    var show = false;
    $(document).on("click", "#control-patient-page", function () {
        show = !show;
        if (show) {
            $(this).text("Show");
            $("#patient-portal").addClass("hidden")
        } else {
            $(this).text("Hide");
            $("#patient-portal").removeClass("hidden");
        }
    })
}