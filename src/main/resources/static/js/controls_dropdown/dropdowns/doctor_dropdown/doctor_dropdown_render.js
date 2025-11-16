import doctorItems from "./handle_items.js";

export default function dropdownDoctorRender() {
    const doctor_dropdowns = [
        $("#doctor-appointment"),
        $("#doctor-manage-patient"),
    ];
     doctorItems(doctor_dropdowns)
}