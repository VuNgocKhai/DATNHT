package com.example.demo.service;

import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    KhachHangDao khachHangDao;
    @Autowired
    BCryptPasswordEncoder pe;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            System.out.println(email);
            KhachHang accounts = khachHangDao.getKhByEmail(email.trim());
            System.out.println(accounts);
//            Tao UserDetail tu Account
            String password = accounts.getMatkhau();
            System.out.println(password);
            return User.withUsername(email).password(pe.encode(password)).roles("USER").build();
        }catch (Exception exception){
            throw new UsernameNotFoundException(email+"not found");
        }
    }
//    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2){
//        String email = oauth2.getPrincipal().getAttribute("email");
//        String password = Long.toHexString(System.currentTimeMillis());
//        UserDetails user= User.withUsername(email)
//                .password(pe.encode(password)).roles("ADMIN").build();
//        Authentication auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }
}
