import {formBooking} from "./form_booking.js";
import bookingInteractive from "./interactiveUI.js";

const bookingUI = () => {
    const bookingPage = $("#bookings-page")
    const html = "/html/bookings/booking.html"

    $.ajax({
        type: "GET",
        url: "/bookings/render-bookings-page",
        success: function (res) {
            if (res) {
                const {metadata, email, roles} = res;
                // ** Render DOM Doctor Portal tu trang html
                $.get(html)
                    .done((html) => {
                        bookingPage.empty();
                        //** Append DOM Booking Page
                        bookingPage.html(html)

                        //** Sau khi Append BookingUI, goi ham tuowng tac voi cac button cua BookingUI
                        bookingInteractive()
                        setTimeout(() => {
                            $("#btn-fast-bookings").on("click", function() {
                                formBooking();
                            })
                        })
                    })
                    .fail(() => {
                        console.error("Không thể tải file HTML:", "/html/portal/doctor/doctor.html");
                        bookingPage.html("<p class='text-red-500'>Không tải được nội dung trang.</p>");
                    })
            } else {

            }
        },
        error: function () {
            console.log("co loi trong qua trinh render DOM doctor-portal");
        }
    })
}
export default bookingUI