import dropdownPatientRender from "./dropdowns/patient_dropdown/patient_dropdown_render.js";
import dropdownDoctorRender from "./dropdowns/doctor_dropdown/doctor_dropdown_render.js";
import dropdownAdminRender from "./dropdowns/admin_dropdown/admin_dropdown_render.js";
import dropdownSuperAdminRender from "./dropdowns/super_admin_dropdown/super_dropdown_render.js";

export default function root() {
    dropdownPatientRender();
    dropdownDoctorRender();
    dropdownAdminRender();
    dropdownSuperAdminRender();
}