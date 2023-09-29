package com.example.demo.config;

import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter  {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;

    @Autowired
    KhachHangDao khachHangDao;

    @Autowired
    HttpSession session;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable();
        http.authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .anyRequest().permitAll();
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/trangchu",false)
                .successHandler(new CustomAuthenticationSuccessHandler(session)) // Đăng ký CustomAuthenticationSuccessHandler
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
        http.logout()
                .logoutUrl("/logout")
                .permitAll();
    }




    @Component
    public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        private final HttpSession httpSession;

        public CustomAuthenticationSuccessHandler(HttpSession httpSession) {
            this.httpSession = httpSession;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            // Lấy thông tin người dùng đã đăng nhập thành công
            String email = authentication.getName();

            // Tìm thông tin khách hàng dựa trên email và lưu vào session
            KhachHang khachHang = khachHangDao.getKhByEmail(email);
            httpSession.setAttribute("khachHangLogin", khachHang);
            // Chuyển hướng đến trang chính sau khi đăng nhập thành công
            response.sendRedirect("/trangchu");
        }
    }
}
