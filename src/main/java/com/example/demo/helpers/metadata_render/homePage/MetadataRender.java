package com.example.demo.helpers.metadata_render.homePage;

import com.example.demo.DTOs.account.ResponseAccountInfo;
import com.example.demo.entities.Account;
import com.example.demo.services.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <div style="color: white; border: 1px solid white; padding:10px">
 *     <h1>RenderControllers</h1>
 *     <p>
 *         <strong>Trong do:</strong>
 *         <ul>
 *             <li>
 *                  <strong>Trang home</strong> chung cho tat ca cac role gom co du lieu chung.
 *             </li>
 *             <li>
 *                  <strong>Footer</strong> lay du lieu thong tin cua he thong, tu khoa, he thong benh vien/phong kham va cac chi nhanh benh vien
 *             </li>
 *             <li>
 *                 <strong>homePage cho tung role:</strong> lay du lieu cua Role lien quan cho Home Page.
 *             </li>
 *         </ul>
 *     </p>
 * </div>
 * **/
public class MetadataRender {
    public static ResponseEntity<?> metadata(
            Authentication authentication,
            AccountService accountService
    ) {
        //** Kiểm tra người dùng đã xác thực chưa:
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

        //** get Email:
        String email = null;
        if (isAuthenticated) email = authentication.getName();

        //** get Role
        var roles = isAuthenticated ?
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                : List.of("ROLE_GUEST");

        Map<String, Object> res = new HashMap<>();
        try {
            if (authentication != null) {
                Account account = accountService.findAccountByEmail(authentication.getName());
                ResponseAccountInfo info = new ResponseAccountInfo();

                info.setEmail(email);
                info.setFirst_name(account.getFirstName());
                info.setLast_name(account.getLastName());
                info.setGender(account.getGender() == 1 ? "Nam" : "Nu");

                res.put("roles", roles);
                res.put("email", email);
                res.put("metadata", info);
            } else {
                res.put("roles",roles);
            }
            res.put("status", HttpStatus.OK.value());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
