import bookingUI from "./bookingUI.js";
import fetchAccountsByFaculty from "./fetch_accounts_by_faculties.js";

export const formBooking = () => {
    const bookingPage = $("#bookings-page")
    const html = "/html/bookings/form_booking.html"

    $.get(html)
        .done((html) => {
            bookingPage.empty();
            bookingPage.html(html)
            fetchAccountsByFaculty();

            $(document).on("click", "#form-bookings-back", function () {
                setTimeout(() => {
                    bookingUI();
                }, 100)
            })
        })
        .fail(() => {
            console.error("Không thể tải file HTML:", "/html/portal/doctor/doctor.html");
            bookingPage.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
        })
}