package com.example.demo.DTOs.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RequestCreateAccount {

    @NotBlank(message = "Họ không được để trống")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    private String lastName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotNull(message = "Ngày sinh không được để trống")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6 đến 20 ký tự")
    private String password;

    @NotBlank(message = "Xác nhận mật khẩu không được để trống")
    private String cfpassword;

    @NotNull(message = "Giới tính không được để trống")
    @Min(value = 1, message = "Giới tính không hợp lệ")
    @Max(value = 2, message = "Giới tính không hợp lệ")
    private Integer gender;

//    @NotBlank(message = "Số điện thoại không được để trống")
//    @Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
//    private String phoneNumber;
//
//    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
//    private String province;
//
//    @NotBlank(message = "Quận/Huyện không được để trống")
//    private String district;
//
//    @NotBlank(message = "Phường/Xã không được để trống")
//    private String ward;
//
//    @NotBlank(message = "Địa chỉ đường không được để trống")
//    private String street;

}
