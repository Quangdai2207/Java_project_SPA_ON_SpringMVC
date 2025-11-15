import sectionMainPage from "./feature_components/section_mainpage.js";
import sectionHoaDon from "./feature_components/section_hoadon.js";
import sectionCaiDat from "./feature_components/section_caidat.js";
import sectionLichKham from "./feature_components/section_lichkham.js";
import sectionDonThuoc from "./feature_components/section_donthuoc.js";

export default function patientControlFeature() {
    const btnControls = {
        mainPage: $("#btn-section-trangchu"),
        appointment: $("#btn-section-lichkham"),
        prescription: $("#btn-section-donthuoc"),
        invoice: $("#patient-btn-section-hoadon"),
        settings: $("#btn-section-caidat"),
    }

    for (let key in btnControls) {
        btnControls[key].on("click", function () {
            const text = btnControls[key].text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "trang chủ" :
                    changeColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionMainPage()
                    break;
                case "lịch khám":
                    changeColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionLichKham();
                    break;
                case "đơn thuốc":
                    changeColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionDonThuoc()
                    break;
                case "hoá đơn":
                    changeColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionHoaDon();
                    break;
                case "cài đặt":
                    changeColor(changeClassButtons, btnControls, text.toLowerCase())
                    sectionCaiDat();
                    break;
                default:
                    return;
            }
        })
    }
}

function changeColor(callback, buttons, name) {

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

