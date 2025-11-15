import sectionAdminMainPage from "./feature_components/section_admin_mainpage.js";
import sectionAdminDoctor from "./feature_components/section_manage_doctor.js";
import sectionAdminPatient from "./feature_components/section_manage_patient.js";
import sectionAdminFaculty from "./feature_components/section_manage_faculty.js";
import sectionAdminAppointment from "./feature_components/section_manage_appointment.js";
import sectionAdminHospital from "./feature_components/section_config_hospotal.js";

export default function adminControlFeature() {
    const btnControls = {
        config: $("#admin-btn-config"),
        doctor: $("#admin-btn-manage-doctor"),
        patient: $("#admin-btn-manage-patient"),
        mainPage: $("#admin-btn-mainpage"),
        faculty: $("#admin-btn-manage-faculty"),
        appointment: $("#admin-btn-manage-appointment"),
    }

    for (let key in btnControls) {
        btnControls[key].on("click", function () {
            const text = btnControls[key].text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "tổng quan" :
                    changesColors(changeClassButtons, btnControls, text.toLowerCase())
                    sectionAdminMainPage();
                    break;
                case "quản lý bệnh nhân":
                    changesColors(changeClassButtons, btnControls, text.toLowerCase())
                    sectionAdminPatient();
                    break;
                case "quản lý bác sĩ":
                    changesColors(changeClassButtons, btnControls, text.toLowerCase())
                    sectionAdminDoctor()
                    break;
                case "quản lý khoa & phòng":
                    changesColors(changeClassButtons, btnControls, text.toLowerCase())
                    sectionAdminFaculty();
                    break;
                case "lịch khám":
                    changesColors(changeClassButtons, btnControls, text.toLowerCase())
                    sectionAdminAppointment();
                    break;
                case "cấu hình bệnh viện":
                    changesColors(changeClassButtons, btnControls, text.toLowerCase())
                    sectionAdminHospital();
                    break;
                default:
                    return;
            }
        })
    }
}

function changesColors(callback, buttons, name) {

    const newClass = "w-full text-left px-3 py-2 cursor-pointer bg-indigo-600 text-white rounded-lg font-medium";
    const baseClass = "w-full text-left px-3 py-2 hover:bg-gray-800 rounded-lg cursor-pointer";

    callback(buttons, baseClass, newClass, name)
}

function changeClassButtons(buttonControls, baseClass, newClass, name) {
    for (let key in buttonControls) {
        const btn = buttonControls[key];
        if (btn.text().replace(/\s+/g, " ").trim().toLowerCase() === name) {
            btn.removeClass(baseClass).addClass(newClass);
        } else {
            btn.removeClass(newClass).addClass(baseClass);
        }
    }
}

