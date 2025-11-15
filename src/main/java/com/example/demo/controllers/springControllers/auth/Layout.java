package com.example.demo.controllers.springControllers.auth;

import com.example.demo.entities.*;
import com.example.demo.helpers.address_helper.AddressHelper;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.address.AddressService;
import com.example.demo.services.phone.PhoneService;
import com.example.demo.services.role.RoleService;
import com.example.demo.services.role_account.RoleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/auth")
public class Layout {
    @Autowired
    private final AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleAccountService roleAccountService;

    public Layout(AccountService accountService) {
        this.accountService = accountService;
    }

    // ** KHU VUC LOGIN TAI KHOAN
    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            ModelMap modelMap,
            Authentication auth
    ) {
        //** Kiem tra da co phien dang nhap chua
        if (auth != null) {
            return "redirect:/home";
        }
        // ** Dang nhap sai put thông báo ra UI
        if (error != null) {
            modelMap.put("msg", "(*) Thông tin Đăng Nhập không đúng!");
        }
        return "auth/login";
    }
    // ** KHU VUC LOGIN TAI KHOAN **

    //** KHU VUC DANG KY TAI KHOAN **
    @GetMapping({"/register"})
    public String page(
            ModelMap modelMap
    ) {
        Account account = new Account();
        Phone phone = new Phone();

        modelMap.put("account", account);
        modelMap.put("phone", phone);

        return "auth/register";
    }

    @PostMapping({"/register"})
    public String page(
            @ModelAttribute("account") Account account,
            @ModelAttribute("phone") Phone phone,
            @RequestParam("province") int province,
            @RequestParam("district") int district,
            @RequestParam("ward") int ward,
            @RequestParam("street") String street,
            @RequestParam("cfpassword") String cfPassword,
            RedirectAttributes redirectAttributes
    ) {
        // ** Kiểm tra mật khẩu và mật khẩu xác nhật:
        if (!account.getPassword().equals(cfPassword)) {
            redirectAttributes.addFlashAttribute("msg", "Mật khẩu không khớp");
            return "redirect:/auth/register";
        }

        //** Kiểm tra định dạng phone:
        if (phone.getPhoneNumber().matches(".*[a-zA-Z].*")) {
            redirectAttributes.addFlashAttribute("msg", "Điện thoại không được chứa ký tự chữ!");
            return "redirect:/auth/register";
        }

        //** Loại bỏ khoảng trắng và các ký tự giữa các số:
        String phoneNumber = phone.getPhoneNumber().replaceAll("[\\s.-]+", ""); // 09090909099 090 0909 09090 090-0090-090 -
        //** Kiểm số lượng ký tự sau khi loại bỏ các khoảng trắng trong điện thoại
        if (!phoneNumber.matches("\\d{10}")) {
            redirectAttributes.addFlashAttribute("msg", "Điện thoại chỉ gồm 10 ký tự số!");
            return "redirect:/auth/register";
        }

        // ** Kiểm tra Email đã đăng ký chưa
        if (accountService.findAccountByEmail(account.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("msg", "Email đã đăng ký, thử lại!");
            return "redirect:/auth/register";
        }

        //** Kiểm tra địa chỉ có thông tin đầy đủ không
        if (province == -1 || ward == -1 || district == -1) {
            redirectAttributes.addFlashAttribute("msg", "Trường địa chỉ không được để trống!");
            return "redirect:/auth/register";
        }

        //** Kiểm tra tên người dùng bằng LastName và FullName
        if (accountService.findAccountByFullName(account.getFirstName(), account.getLastName()) != null) {
            redirectAttributes.addFlashAttribute("msg", "Người dùng này đã Đăng Ký, Thủ lại!");
            return "redirect:/auth/register";
        }

        String provinceName = AddressHelper.getProvinceName(province).replaceAll("^(Thành phố |Tỉnh |Huyện |Xã )", "");
        String districtName = AddressHelper.getDistrictName(district).replaceAll("^(Quận |Huyện |Thành phố |Xã )", "");
        String wardName = AddressHelper.getWardName(district, ward).replaceAll("^(Phường |Xã |Thị trấn |Ấp |Thôn )", "");
        String streetName = street.replaceAll("^\\d+\\s*", ""); // loại bỏ số
        String addressNumber = street.replaceAll("[^\\d]", ""); // loại bo chữ

        // ** Tao moi tai khoan voi cac thuoc tinh mac dinh cho nguoi dung la benh nhan:
        account.setFaculty(null);
        account.setPassword(encoder.encode(account.getPassword()));

        // ** Neu luu account thanh cong thi tao dia chi nguoi dung
        Address address = new Address();
        address.setProvince(provinceName);
        address.setDistrict(districtName);
        address.setWard(wardName);
        address.setStreet(streetName);
        address.setAddressNumber(addressNumber);

        // ** Tao ID cho Phone Model va set account cho Phone
        phone.setPhoneNumber(phoneNumber);

        // ** Lưu vào DB lần lượt các đối tượng được tạo trong Register:
        boolean isSaveAccount = accountService.save(account);

        address.setAccount(account);
        phone.setAccount(account);
        boolean isAddressSave = addressService.save(address);
        boolean isPhoneSave = phoneService.save(phone);

        // ** tao role cho nguoi dung la benh nhan
        Role role = roleService.findById(4);
        RoleAccount roleAccount = new RoleAccount();
        roleAccount.setRole(role);
        roleAccount.setAccount(account);
        boolean isSavedRoleAccount = roleAccountService.save(roleAccount);

        boolean isCompleted = false;
        if (isSaveAccount && isAddressSave && isPhoneSave && isSavedRoleAccount) {
            isCompleted = true;
        }

        if (isCompleted) {
            redirectAttributes.addFlashAttribute("msg", "Đăng Ký thành công, Đăng Nhập tài khoản của bạn");
            redirectAttributes.addFlashAttribute("email", account.getEmail());
            return "redirect:/home" + "?register=201&email=" + account.getEmail();
        }
        redirectAttributes.addFlashAttribute("msg", "Đăng ký thất bại, thử lại!");
        return "forward:/auth/register";
    }
    // ** KHU VUC DANG KY TAI KHOAN

    @GetMapping({"/access-denied"})
    public String access_denied() {
        return "auth/access_denied";
    }
}
