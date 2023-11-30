package com.example.demo.controller;

import com.example.demo.entity.KhachHang;
import com.example.demo.repository.KhachHangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XepHangThanhVienController {

    @Autowired
    KhachHangDao khachHangDao;

    private Authentication authentication;

    @GetMapping("/qltk-kh/hang-thanh-vien")
    public String index(Model model){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang=  khachHangDao.getKhByEmail(authentication.getName());

        model.addAttribute("khachHang", khachHang);
        return "qltk_kh/xephangthanhvien";
    }
}
