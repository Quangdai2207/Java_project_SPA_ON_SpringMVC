import {index} from "../../../pages_first_render/home/index.js";
import {DOCTOR_PORTAL} from "../../../pages_first_render/doctor/portal.js";
import {PATIENT_PORTAL} from "../../../pages_first_render/patient/portal.js";
import {ADMIN_DASHBOARD} from "../../../pages_first_render/admin/dashboard.js";
import {SUPER_DASHBOARD} from "../../../pages_first_render/super_admin/dashboard.js";

export default function superAdminItems(dropdown) {
    dropdown.forEach((item) => {
        if (!item) return;
        item.on("click", function () {
            const text = item.text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "quản lý tài khoản":
                    const manageAccountHtml = "/html/dropdown/super_admin/manage_account.html";
                    renders(renderPageTarget, manageAccountHtml)
                    break;
                case "phân quyền hệ thống":
                    const middleware_html = "/html/dropdown/super_admin/middleware.html";
                    renders(renderPageTarget, middleware_html)
                    break;
                case "nhật ký hệ thống":
                    const logSystem_html = "/html/dropdown/super_admin/log_system.html";
                    renders(renderPageTarget, logSystem_html)
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
    const rootLayout = $("#root")

    //** Dashboard pages
    const doctor_page = $("#doctor-page");
    const patient_page = $("#patient-page");
    const admin_page = $("#admin-page");
    const super_page = $("#super-admin-page");
    callback(path, rootLayout, home_page, doctor_page, patient_page, admin_page, super_page, pageTarget)
}

function renderPageTarget
(path,
 rootPage,
 home_page,
 doctor_page,
 patient_page,
 adminPage,
 superPage,
 pageTarget
) { // ✅ $item thể hiện là jQuery object
    //** hover vao dropdown hiwn thi cac nut bam va click vao nut Profile de bat/tat home va layout
    if (path === "/" || path === "/home" || path === "/home/index") appendHtml(home_page, pageTarget)
    if (path === "/patient" || path === "/patient/portal") appendHtml(patient_page, pageTarget)
    if (path === "/doctor" || path === "/doctor/portal") appendHtml(doctor_page, pageTarget)
    if (path === "/admin" || path === "/admin/dashboard") appendHtml(adminPage, pageTarget)
    if (path === "/bookings") appendHtml(rootPage, pageTarget)
    appendHtml(superPage, pageTarget)
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
    $(document).on("click", "#btn-super-dropdown-back", function () {
        if (path === "/" || path === "/home" || path === "/home/index") index();
        if (path === "/patient" || path === "/patient/portal") PATIENT_PORTAL();
        if (path === "/doctor" || path === "/doctor/portal") DOCTOR_PORTAL();
        if (path === "/admin" || path === "/admin/dashboard") ADMIN_DASHBOARD();
        if (path === "/bookings") location.reload();
        SUPER_DASHBOARD();
    })
}














