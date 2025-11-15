export default function bookingInteractive() {
    $(document).ready(function () {
        const $filterDynamic = $("#filterDynamic");
        const btns = {
            clinic: $("#btnClinic"),
            hospital: $("#btnHospital")
        };

        // Mặc định chọn "Phòng khám"
        setActive("clinic", btns);
        renderClinicFilter($filterDynamic);

        btns.clinic.on("click", function () {
            setActive("clinic", btns);
            renderClinicFilter($filterDynamic);
        });

        btns.hospital.on("click", function () {
            setActive("hospital", btns);
            renderHospitalFilter($filterDynamic);
        });
    });
}


// Hàm set màu nút
function setActive(activeKey, btns) {
    for (let key in btns) {
        const btn = btns[key];
        btn.removeClass("bg-orange-600 text-white").addClass("bg-gray-700 text-gray-300 hover:bg-gray-600");

        if (key === activeKey) {
            btn.removeClass("bg-gray-700 text-gray-300 hover:bg-gray-600").addClass("bg-orange-600 text-white");
        }
    }
}

// Bộ lọc Phòng khám
function renderClinicFilter(dynamic) {
    dynamic.html(`
            <select class="flex-1 p-3 rounded-lg bg-gray-800 border border-gray-700 text-gray-200 focus:ring-2 focus:ring-orange-500 outline-none">
                <option>Chọn loại phòng khám</option>
                <option>Đa khoa</option>
                <option>Da liễu</option>
                <option>Răng hàm mặt</option>
                <option>Sản phụ khoa</option>
            </select>
            <select class="flex-1 p-3 rounded-lg bg-gray-800 border border-gray-700 text-gray-200 focus:ring-2 focus:ring-orange-500 outline-none">
                <option>Chọn khung giờ làm việc</option>
                <option>Sáng</option>
                <option>Chiều</option>
                <option>Tối</option>
            </select>
        `);
}

// Bộ lọc Bệnh viện
function renderHospitalFilter(dynamic) {
    dynamic.html(`
            <select class="flex-1 p-3 rounded-lg bg-gray-800 border border-gray-700 text-gray-200 focus:ring-2 focus:ring-orange-500 outline-none">
                <option>Chọn chuyên khoa</option>
                <option>Tim mạch</option>
                <option>Nội tổng quát</option>
                <option>Chấn thương chỉnh hình</option>
                <option>Ung bướu</option>
            </select>
            <select class="flex-1 p-3 rounded-lg bg-gray-800 border border-gray-700 text-gray-200 focus:ring-2 focus:ring-orange-500 outline-none">
                <option>Chọn quy mô bệnh viện</option>
                <option>Quốc tế</option>
                <option>Tư nhân</option>
                <option>Công lập</option>
            </select>
        `);
}