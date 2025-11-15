export const address = () => {

    //** Loads tỉnh thành cho combobox chọn tỉnh thành
    fetch("https://provinces.open-api.vn/api/p/")
        .then(res => res.json())
        .then(data => {
            data.forEach(p => {
                $("#province").append(`<option value="${p.code}">${p.name}</option>`);
            });
        });

    //** Khi thanh đổi tỉnh thành lấy code của tình thành đó truyền cho route chi tiết tỉnh thành
    $(document).on("change", "#province", function () {
        const provinceCode = $(this).val();
        $("#district").html('<option value="">Chọn Quận/Huyện</option>');
        $("#ward").html('<option value="">Chọn Phường/Xã</option>');
        if (!provinceCode) return;

        fetch(`https://provinces.open-api.vn/api/p/${provinceCode}?depth=2`)
            .then(res => res.json())
            .then(data => {
                data.districts.forEach(d => {
                    $("#district").append(`<option value="${d.code}">${d.name}</option>`);
                });
            });
    });

    //** Sau khi vào route chi tiết tỉnh thành, lấy code district truyền vào route chi tiết của districts
    $(document).on("change", "#district", function () {
        const districtCode = $(this).val();
        $("#ward").html('<option value="">Chọn Phường/Xã</option>');
        if (!districtCode) return;

        fetch(`https://provinces.open-api.vn/api/d/${districtCode}?depth=2`)
            .then(res => res.json())
            .then(data => {
                data.wards.forEach(w => {
                    $("#ward").append(`<option value="${w.code}">${w.name}</option>`);
                });
            });
    });
}