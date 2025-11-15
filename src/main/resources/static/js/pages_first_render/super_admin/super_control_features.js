import sectionSuperMainPage from "./feature_components/section_mainpage.js";
import sectionSuperManageAccountPage from "./feature_components/section_manage_account.js";
import sectionSuperManageDoctorPage from "./feature_components/section_manage_doctor.js";
import sectionSuperManageHospitalPage from "./feature_components/section_manage_hospital.js";
import sectionSuperManageLoglPage from "./feature_components/section_manage_log_system.js";
import sectionSuperSystemConfigPage from "./feature_components/section_system_config.js";

export default function superControlFeature() {
    const btnControls = {
        account: $("#super-btn-manage-account"),
        doctor: $("#super-btn-manage-doctor"),
        hospital: $("#super-btn-manage-hospital"),
        mainPage: $("#super-btn-mainpage"),
        log: $("#super-btn-log-system"),
        system: $("#super-btn-system-config"),
    }

    for (let key in btnControls) {
        btnControls[key].on("click", function () {
            const text = btnControls[key].text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "tổng quan" :
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionSuperMainPage();
                    break;
                case "quản lý tài khoản":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionSuperManageAccountPage();
                    break;
                case "quản lý bác sĩ":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionSuperManageDoctorPage();
                    break;
                case "quản lý bệnh viện":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionSuperManageHospitalPage();
                    break;
                case "nhật ký & log hệ thống":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionSuperManageLoglPage();
                    break;
                case "cấu hình hệ thống":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionSuperSystemConfigPage();
                    break;
                default:
                    return;
            }
        })
    }
}

function changesColor(callback, buttons, name) {

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

