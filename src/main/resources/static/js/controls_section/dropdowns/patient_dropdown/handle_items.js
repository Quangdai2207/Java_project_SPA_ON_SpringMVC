import {index} from "../../../pages_first_render/home/index.js";
import {PATIENT_PORTAL} from "../../../pages_first_render/patient/portal.js";


//?? FUNCTION CHÍNH HIỂN THỊ CÁC DROPDOWN SECTION CHO PAGE LAYOUT PATIENT
export default function patientItems(dropdown) {
    dropdown.forEach((item) => {
        if (!item) return;
        item.on("click", function () {
            const text = item.text().replace(/\s+/g, " ").trim();
            switch (text.toLowerCase()) {
                case "hồ sơ cá nhân":
                    const profile_html = "/html/dropdown/patient/profile.html";
                    renders(renderPageTarget, profile_html)
                    break;
                case "lịch khám":
                    const appointment = "/html/dropdown/patient/appointment.html"
                    renders(renderPageTarget, appointment)
                    break;
                case "hồ sơ bệnh án":
                    const profile_appointment = "/html/dropdown/patient/profile_appointment.html"
                    renders(renderPageTarget, profile_appointment)
                    break;
                case "thông báo":
                    const alertPage = "/html/dropdown/patient/alert.html"
                    renders(renderPageTarget, alertPage)
                    break;
                default:
                    return;
            }
        })
    });
}

//?? HIỂN THỊ SECTION MỤC TIÊU CHO PAGE LAYOUT PATIENT
function renders(callback, pageTarget) {
    //** Get current pathname
    const path = location.pathname
    //** Homepage by ROLEs
    const home_page = $("#home-page")
    const root_layout = $("#root")

    //** Dashboard pages
    const patient_page = $("#patient-page");
    callback(path, root_layout, home_page, patient_page, pageTarget)
}

function renderPageTarget(path, rootPage, home_page, patient_page, pageTarget) { // ✅ $item thể hiện là jQuery object
    //** hover vao dropdown hiwn thi cac nut bam va click vao nut Profile de bat/tat home va layout
    if (path === "/" || path === "/home" || path === "/home/index") appendHtml(home_page, pageTarget)
    if (path === "/bookings" || path === "/bookings/") appendHtml(rootPage, pageTarget)
    appendHtml(patient_page, pageTarget)
    btnBackProfile(path)
    data(pageTarget)
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

function btnBackProfile(path) {
    $(document).on("click", "#btn-patient-dropdown-back", function () {
        if (path === "/" || path === "/home" || path === "/home/index") index();
        if (path === "/bookings" || path === "/bookings/") location.reload()
        PATIENT_PORTAL();
    })
}

function data(pageTarget) {
    var pageTargets = [
        "/html/dropdown/patient/profile.html",
        "/html/dropdown/patient/appointment.html",
        "/html/dropdown/patient/profile_appointment.html",
        "/html/dropdown/patient/alert.html"
    ];

    var pageName = "";
    pageTargets.map(page => {
        if (page === pageTarget) {
            pageName = page.split("/")[4].replace(".html", "").trim();
            metadata(pageName);
        }
    })
}

function metadata(pageName) {
    $.ajax({
        type: "GET",
        url: `/api/v1/patient/${pageName}`,
        success: function (res) {
            if (res.message === "OK") {
                switch (pageName) {
                    case "profile" :
                        const {profile} = res
                        profileSection(profile);
                        break;
                    case "appointment" :
                        const {appointments} = res
                        appointmentSection(appointments)
                        break;
                    case "profile_appointment" :
                        const {medicalRecords} = res;
                        medicalRecordSection(medicalRecords);
                        break;
                    case "alert" :
                        break;
                }
            }
        },
        error: function () {
            console.log("Da co loi trong khi nap du lieu...");
        }
    })
}

function profileSection(data) {
    let dob = "";
    if (data.dateOfBirth) {
        const date = new Date(data.dateOfBirth);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        dob = `${day}/${month}/${year}`;
    }

    let phone = data.phones; // ví dụ "0901 399 931"
    phone = phone.replace(/(\d{4})(\d{3})(?=\d)/g, "$1 $2 ");

    setTimeout(() => {
        $("#profile-dropdown-fullName").text(data.fullName)
        $("#profile-dropdown-profileCode").text(data.generalMedicalInfo[0].id)
        $("#profile-dropdown-info").empty().html(` 
            <li id="profile-dropdown-body-fullName"><span class="text-gray-400 w-32 inline-block">Họ và tên:</span>${data.fullName}</li>
            <li id="profile-dropdown-body-dob"><span class="text-gray-400 w-32 inline-block">Ngày sinh:</span>${dob}</li>
            <li id="profile-dropdown-body-gender"><span class="text-gray-400 w-32 inline-block">Giới tính:</span>${data.gender}</li>
            <li id="profile-dropdown-body-phone"><span class="text-gray-400 w-32 inline-block">Số điện thoại:</span>${phone}</li>
            <li id="profile-dropdown-body-address" class="flex items-start">
              <span class="text-gray-400 w-32 shrink-0">Địa chỉ:</span>
              <span class="flex-1 break-words">${data.addresses}</span>
            </li>
            <li id="profile-dropdown-body-email"><span class="text-gray-400 w-32 inline-block">Email:</span>${data.email}</li>
        `)

        if (data.generalMedicalInfo.length === 0) {
            $("#profile-dropdown-generalMedicalInfo").empty().html("<h4 class='text-orange-400 font-bold text-center text-lg'>Thông tin y tế chưa cập nhật</h4>");
        } else {
            $("#profile-dropdown-generalMedicalInfo").empty().html(`
            <li><span class="text-gray-400 w-40 inline-block">Nhóm máu:</span>${data.generalMedicalInfo[0].bloodType} (type)</li>
            <li><span class="text-gray-400 w-40 inline-block">Chiều cao:</span>${data.generalMedicalInfo[0].height} (cm)</li>
            <li><span class="text-gray-400 w-40 inline-block">Cân nặng:</span>${data.generalMedicalInfo[0].weight} (kg)</li>
            <li><span class="text-gray-400 w-40 inline-block">Tiền sử bệnh lý:</span>${data.generalMedicalInfo[0].pastMedicalHistory}</li>
            <li><span class="text-gray-400 w-40 inline-block">Dị ứng:</span>${data.generalMedicalInfo[0].allergies}</li>
            <li><span class="text-gray-400 w-40 inline-block">Thói quen:</span>${data.generalMedicalInfo[0].lifestyleHabits}</li>
        `)
        }
    }, 10)
}


function medicalRecordSection(data) {
    if (data != null) {
        const medicalRecords = data
        if (medicalRecords.length === 0) {
            $("#profile-appointment-table-list")
                .empty()
                .html("<tr><td colspan='8' class='text-center pt-5 pb-5 font-bold text-sm'>Chưa có lịch khám nào</td></tr>");
            return;
        }
        setTimeout(() => {
            $("#profile-appointment-table-list").empty();
            medicalRecords.map(item => {
                $("#profile-appointment-table-list").html(
                    `<tr class="hover:bg-gray-800 transition">
                                <td class="border border-gray-800 p-2">${item.id}</td>
                                <td class="border border-gray-800 p-2">${item.doctor}</td>
                                <td class="border border-gray-800 p-2">${item.faculty}</td>
                                 <td class="border border-gray-800 p-2">${item.createdDate}</td>
                                <td class="border border-gray-800 p-2">${item.content}</td>
                                <td class="border border-gray-800 p-2 flex justify-center">
                                    <span class="px-3 py-1 bg-green-600/20 text-green-400 rounded-full text-xs font-semibold">${item.status}</span>
                                </td>
                                <td class="border border-gray-800 p-2 space-x-2">
                                    <button data-value="${item.id}" class="profile-appointment-btn-view px-3 py-1 bg-indigo-600 hover:bg-indigo-700 rounded-lg text-white text-xs font-medium">Xem</button>
                                    <button data-value="${item.id}" class="profile-appointment-btn-note px-3 py-1 bg-yellow-600 hover:bg-yellow-700 rounded-lg text-white text-xs font-medium">Note</button>
                                </td>
                            </tr>`
                )
            })
            const table = $("#profile-appointment-table-list tr");
            const filter = $("#profile-appointment-dropdown-filter-record-date");
            const searchInput = $("#profile-appointment-dropdown-search-record");
            // ** Lang nghe su kien buttons "Chi tiet" sau khi render dropdown appointment
            table.on("click", ".profile-appointment-btn-view", function () {
                alert(`Chi tiết: ${$(this).data("value")}`);
            });

            // ** Lang nghe su kien buttons "huy" sau khi render dropdown appointment
            table.on("click", ".profile-appointment-btn-note", function () {
                alert(`Huỷ: ${$(this).data("value")}`);
            });

            searchOnTable(table, filter, searchInput)
        }, 10)
    }
}

function appointmentSection(data) {
    const appointments = Array.isArray(data) ? data : [];
    if (appointments.length === 0) {
        $("#appointment-table-list-appointments")
            .empty()
            .html("<tr><td colspan='8' class='text-center pt-5 pb-5 font-bold text-sm'>Chưa có lịch khám nào</td></tr>");
        return;
    }
    setTimeout(() => {
        $("#appointment-table-list-appointments").empty();
        appointments.map(item => {
            let dates = "";
            if (item.date) {
                const date = new Date(item.date);
                const day = String(date.getDate()).padStart(2, '0');
                const month = String(date.getMonth() + 1).padStart(2, '0');
                const year = date.getFullYear();
                dates = `${day}/${month}/${year}`;
            }
            $("#appointment-table-list-appointments").append(`
                <tr class="hover:bg-gray-800 transition">
                    <td class="border border-gray-800 p-2">${item.id}</td>
                    <td class="border border-gray-800 p-2">${item.doctor}</td>
                    <td class="border border-gray-800 p-2">${item.facultyName}</td>
                    <td class="border border-gray-800 p-2">${dates}</td>
                    <td class="border border-gray-800 p-2">${item.time}</td>
                    <td class="border border-gray-800 p-2">
                        <span class="px-3 py-1 bg-green-600/20 text-green-400 rounded-full text-xs font-semibold">${item.status}</span>
                    </td>
                    <td class="border border-gray-800 p-2 text-center">
                        <button data-value="${item.id}" class="appointment-dropdown-btn-details cursor-pointer px-3 py-1 bg-indigo-600 hover:bg-indigo-700 rounded-lg text-white text-xs font-medium mr-2">Chi tiết</button>
                        <button data-value="${item.id}" class="appointment-dropdown-btn-cancel cursor-pointer px-3 py-1 bg-red-600 hover:bg-red-700 rounded-lg text-white text-xs font-medium">Hủy</button>
                    </td>
                </tr>
            `);
        });
        const table = $("#appointment-table-list-appointments tr");
        const filter = $("#appointment-dropdown-filter-appointment-date")
        const searchInput = $("#appointment-dropdown-search-appointment")
        // ** Lang nghe su kien buttons "Chi tiet" sau khi render dropdown appointment
        table.on("click", ".appointment-dropdown-btn-details", function () {
            alert(`Chi tiết: ${$(this).data("value")}`);
        });

        // ** Lang nghe su kien buttons "huy" sau khi render dropdown appointment
        table.on("click", ".appointment-dropdown-btn-cancel", function () {
            alert(`Huỷ: ${$(this).data("value")}`);
        });
        searchOnTable(table, filter, searchInput);
    }, 100);
}

//** function cho chuc namg tim kiem va filter sau khi render section va du lieu section tuong ung **//
//** Search va filter
function searchOnTable($table, filter, searchInput) {
    searchInput.on("input", function () {
        const keyword = $(this).val().toLowerCase();

        $table.each(function () {
            const cols = [0, 1, 2, 3, 4, 5]
            var name = "";

            cols.forEach(i => {
                const cell = $(this).children().eq(i).text().toLowerCase();
                name += cell + " ";
            })
            $(this).toggle(name.includes(keyword));
        });
    })

    //** Filter Date
    filter.on("change", function () {
        const val = $(this).val();
        if (!val) return;

        const date = new Date(val);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        const dateFormat = `${day}/${month}/${year}`;

        $table.each(function () {
            const dateText = $(this).children().eq(3).text().trim();
            $(this).toggle(dateText.includes(dateFormat));
        });
    });
}
