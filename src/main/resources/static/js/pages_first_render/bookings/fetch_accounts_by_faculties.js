export default function fetchAccountsByFaculty() {
    $.ajax({
        type: "GET",
        url: "/patient/api/v1/faculties",
        success: function (data) {
            if (data.status === "OK" || data !== null) {
                const faculties = data.faculties;

                // Xóa option cũ và thêm option mặc định
                const $select = $("#faculties");
                $select.html('<option value="">-- Chọn khoa --</option>');

                // Thêm option mới
                faculties.forEach(f => {
                    $select.append(`<option value="${f.id}">${f.name}</option>`);
                });

                // Gán event change (nếu chưa gán)
                $select.off("change").on("change", function () {
                    const facultyID = $(this).val();
                    fetchDoctors(facultyID);
                });
            } else {
                console.warn("Khong thay tim thay du lieu")
            }
        },
        error: function () {
            console.error("Co loi trong qua trinh nap du lieu")
        }
    })
}

function fetchDoctors(facultyID) {
    $("#doctor").html('<option value="">Chọn bác sĩ</option>');
    if (!facultyID) return;

    $.ajax({
        type: "GET",
        url: `/patient/api/v1/doctor?faculty=${facultyID}`,
        success: function (data) {
            if (data.status === "OK" && data.doctors.length > 0) {
                const doctors = data.doctors;
                doctors.map(d => {
                    $("#doctor").append(`<option value="${d.id}">${d.fname} ${d.lname}</option>`);
                });
            }
        },
        error: function () {
            console.error("Co loi trong qua trinh nap du lieu")
        }
    })
}