import sectionMyPatient from "./feature_components/section_mypatient.js";
import sectionPrescription from "./feature_components/section_prescription.js";
import sectionMedicalRecord from "./feature_components/section_medical_record.js";
import sectionReport from "./feature_components/section_report.js";
import sectionAppointment from "./feature_components/section_appointment.js";
import sectionMainPage from "./feature_components/section_mainpage.js";

export default function doctorControlFeature() {
    const btnControls = {
        appointment: $("#doctor_appointment"),
        myPatient: $("#doctor_my_patient"),
        medicalRecord: $("#doctor_medical_record"),
        mainPage: $("#doctor_mainpage"),
        prescription: $("#doctor_prescription"),
        report: $("#doctor_system_report"),
    }

    for (let key in btnControls) {
        btnControls[key].on("click", function () {
            const text = btnControls[key].text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "dashboard" :
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionMainPage();
                    break;
                case "bệnh nhân của tôi":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionMyPatient();
                    break;
                case "lịch khám":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionAppointment()
                    break;
                case "đơn thuốc":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionPrescription();
                    break;
                case "hồ sơ y tế":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionMedicalRecord();
                    break;
                case "báo cáo & thống kê":
                    changesColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionReport()
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

