package com.example.demo.enums;

public enum Role {
    ROLE_SUPER_ADMIN,
    ROLE_ADMIN,
    ROLE_DOCTOR,
    ROLE_PATIENT,
    ROLE_GUEST;

    public String getRole() {
        return this.name();
    }
}
