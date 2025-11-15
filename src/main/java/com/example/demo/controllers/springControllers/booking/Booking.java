package com.example.demo.controllers.springControllers.booking;

import com.example.demo.entities.Appointment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bookings")
public class Booking {
    @GetMapping({"", "/"})
    public String bookings(ModelMap modelMap) {
        Appointment appointment = new Appointment();
        modelMap.put("appointment", appointment);
        return "booking/page";
    }

    @PostMapping({"", "/"})
    public String bookings(
            @ModelAttribute("appointment") Appointment appointment,
            RedirectAttributes redirectAttributes) {
        System.out.println(appointment.toString());
        redirectAttributes.addFlashAttribute("msg", "");
        return "page";
    }
}
