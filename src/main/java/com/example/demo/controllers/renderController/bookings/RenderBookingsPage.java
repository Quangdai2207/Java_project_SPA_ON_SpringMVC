package com.example.demo.controllers.renderController.bookings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class RenderBookingsPage {
    @GetMapping(value = "/render-bookings-page", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> page() {
        try {
            return new ResponseEntity<>(200, HttpStatus.OK);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
