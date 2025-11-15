import {index} from "../../../pages_first_render/home/index.js";
import {DOCTOR_PORTAL} from "../../../pages_first_render/doctor/portal.js";
import {PATIENT_PORTAL} from "../../../pages_first_render/patient/portal.js";
import {ADMIN_DASHBOARD} from "../../../pages_first_render/admin/dashboard.js";

export default function adminItems(dropdown) {
    dropdown.forEach((item) => {
        if (!item) return;
        item.on("click", function () {
            const text = item.text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "quản lý bệnh nhân":
                    const managePatientHtml = "/html/dropdown/admin/manage_patient.html";
                    renders(renderPageTarget, managePatientHtml)
                    break;
                case "báo cáo thống kê":
                    const report_html = "/html/dropdown/admin/reports.html";
                    renders(renderPageTarget, report_html)
                    break;
                case "quản lý bác sĩ":
                    const manageDoctorHtml = "/html/dropdown/admin/manage_doctor.html";
                    renders(renderPageTarget, manageDoctorHtml)
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
    const patient_page = $("#patient-page");
    const admin_page = $("#admin-page");
    const rootLayout = $("#root")
    callback(path, rootLayout, home_page, doctor_page, patient_page, admin_page, pageTarget)
}

function renderPageTarget(path, rootPage, home_page, doctor_page, patient_page, adminPage, pageTarget) { // ✅ $item thể hiện là jQuery object
    //** hover vao dropdown hiwn thi cac nut bam va click vao nut Profile de bat/tat home va layout
    if (path === "/" || path === "/home" || path === "/home/index") appendHtml(home_page, pageTarget)
    if (path === "/patient" || path === "/patient/portal") appendHtml(patient_page, pageTarget)
    if (path === "/doctor" || path === "/doctor/dashboard") appendHtml(doctor_page, pageTarget)
    if (path === "/bookings") appendHtml(rootPage, pageTarget)
    appendHtml(adminPage, pageTarget)
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
    $(document).on("click", "#btn-admin-dropdown-back", function () {
        if (path === "/" || path === "/home" || path === "/home/index") index();
        if (path === "/patient" || path === "/patient/portal") PATIENT_PORTAL();
        if (path === "/doctor" || path === "/doctor/portal") DOCTOR_PORTAL();
        if (path === "/bookings") location.reload();
        ADMIN_DASHBOARD();
    })
}