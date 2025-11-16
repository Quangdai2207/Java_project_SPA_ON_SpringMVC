import {index} from "../../../pages_first_render/home/index.js";
import {DOCTOR_PORTAL} from "../../../pages_first_render/doctor/portal.js";
import {PATIENT_PORTAL} from "../../../pages_first_render/patient/portal.js";

export default function doctorItems(dropdown) {
    dropdown.forEach((item) => {
        if (!item) return;
        item.on("click", function () {
            const text = item.text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "bệnh nhân của tôi":
                    const myPatientHTml = "/html/dropdown/doctor/my_patients.html";
                    renders(renderPageTarget, myPatientHTml);
                    break;
                case "hồ sơ bệnh án":
                    const appointment_html = "/html/dropdown/doctor/appointment.html";
                    renders(renderPageTarget, appointment_html);
                    break;
                default:
                    return;
            }
        })
    });
}

function renders(callback, pageTarget) {
    //** Get current pathname
    const path = location.pathname
    //** Homepage by ROLEs
    const home_page = $("#home-page")

    //** Dashboard pages
    const doctor_page = $("#doctor-page");
    const patient_page = $("#patient-page")
    const rootLayout = $("#root")

    //** goi lai ham render chho trang dropdown can duoc render
    callback(path, rootLayout, home_page, doctor_page, patient_page, pageTarget)
}


function renderPageTarget(path, rootPage, home_page, doctor_page, patient_page, pageTarget) { // ✅ $item thể hiện là jQuery object
    //** hover vao dropdown hiwn thi cac nut bam va click vao nut Profile de bat/tat home va layout
    if (path === "/" || path === "/home" || path === "/home/index") appendHtml(home_page, pageTarget)
    if (path === "/patient" || path === "/patient/portal") appendHtml(patient_page, pageTarget)
    if (path === "/bookings") appendHtml(rootPage, pageTarget)
    appendHtml(doctor_page, pageTarget)
    goBack(path);
}

//** Render account profile
function appendHtml(root, pageTarget) {
    root.empty()
    $.get(pageTarget)
        .done((html) => {
            root.html(html)
        })
        .fail(() => {
            console.error("Không thể tải file HTML:", pageTarget);
            root.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}

function goBack(path) {
    $(document).on("click", "#btn-doctor-dropdown-back", function () {
        if (path === "/" || path === "/home" || path === "/home/index") index();
        if (path === "/patient" || path === "/patient/portal") PATIENT_PORTAL();
        if (path === "/bookings") location.reload();
        DOCTOR_PORTAL();
    })
}