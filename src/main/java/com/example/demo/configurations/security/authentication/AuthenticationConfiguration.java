package com.example.demo.configurations.security.authentication;

import com.example.demo.configurations.security.authentication.components.CustomSuccessHandler;
import com.example.demo.enums.Role;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationConfiguration implements WebMvcConfigurer {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(
                            "/", // cho phep truy cap trang chu mac dinh
                            "/home/**", // cho phep truy cap trang home mac dinh
                            "/js/**", // Cho phep truy cap cac file static
                            "/assets/**", // Cho phep truy cap tai nguyen trong static
                            "/favicon.io", // cho phep cac icon
                            "/about/**",// cho phep vao trang about khong can xac thuc
                            "/auth/login/**",
                            "/auth/register/**",
                            "/css/**",
                            "/error", "/html/**",
                            "/ws/**", "/app/**",
                            "/api/v1/account/register/**"
                    ).permitAll()
                    .requestMatchers("/super-admin/**").hasAnyAuthority(Role.ROLE_SUPER_ADMIN.getRole())
                    .requestMatchers("/admin/**").hasAnyAuthority(Role.ROLE_ADMIN.getRole(), Role.ROLE_SUPER_ADMIN.name())
                    .requestMatchers("/doctor/**").hasAnyAuthority(
                            Role.ROLE_DOCTOR.getRole(),
                            Role.ROLE_SUPER_ADMIN.name(),
                            Role.ROLE_ADMIN.getRole()
                    )
                    .requestMatchers(
                            "/patient/**",
                            "/account/change-password/**",
                            "/account/profile/**",
                            "/bookings/**",
                            "/api/v1/**"
                    ).hasAnyAuthority(
                            Role.ROLE_PATIENT.getRole(),
                            Role.ROLE_DOCTOR.getRole(),
                            Role.ROLE_SUPER_ADMIN.name(),
                            Role.ROLE_ADMIN.getRole())
                    .anyRequest().authenticated();
        });
        http.formLogin(form -> {
            form
                    .loginPage("/auth/login")    //? page login from project instead default login page spring
                    .loginProcessingUrl("/auth/process-login")   //? Route handle POST method login
                    .usernameParameter("email")     //? Define email field
                    .passwordParameter("password")  //? Define password field
                    .successHandler(customSuccessHandler)     //? Khi Dang nhap thanh cong, spring tu dieu huong ve trang cua cac role
//                    .defaultSuccessUrl("/?logged=success", true)
                    .failureUrl("/auth/login?error") //? Redirect login with "error" parameter on URL if login failure
            ;
        });
        //? Xu ly Logout
        http.logout(log -> {
                    log
                            .logoutUrl("/auth/logout") //? Khai bao route logout
                            .logoutSuccessUrl("/"); //? Logout thanh cong quay ve trang login
                })
                // * Cau hinh cho trang access denied khi khong co quyen truy cap va cac route cua role khac
                .exceptionHandling(ex -> {
                    ex.accessDeniedPage("/auth/access-denied");
                });
        return http.build();
    }
}
