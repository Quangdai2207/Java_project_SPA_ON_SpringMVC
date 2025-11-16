import {index} from "./pages_first_render/home/index.js";
import {ADMIN_DASHBOARD} from "./pages_first_render/admin/dashboard.js";
import {SUPER_DASHBOARD} from "./pages_first_render/super_admin/dashboard.js";
import {DOCTOR_PORTAL} from "./pages_first_render/doctor/portal.js";
import {PATIENT_PORTAL} from "./pages_first_render/patient/portal.js";
import {address} from "./pages_first_render/auths/fetch_address.js";
import {theme} from "./pages_first_render/auths/theme_layout.js";
import {theme_layout} from "./pages_first_render/home/layout_theme.js";
import contact_patient_portal from "./pages_first_render/patient/interact_layout.js";
import root from "./controls_dropdown/root.js";
import bookingUI from "./pages_first_render/bookings/bookingUI.js";

// ** Module main.js, Noi dang ky render DOM cho cac pages
$(document).ready(function () {
    //** Render HomePage
    index()

    //** Render Booking UI
    bookingUI();

    //** Render cac page khi lan dau truy cap vao dung dung website
    ADMIN_DASHBOARD()
    SUPER_DASHBOARD()
    DOCTOR_PORTAL()
    PATIENT_PORTAL()

    //** Fetch dia chi cho page dang ky - register
    address()

    //** Theme toan cuc
    theme()
    theme_layout()
    contact_patient_portal()

    //** Render cac section Dropdown cua rung Role
    root();
});