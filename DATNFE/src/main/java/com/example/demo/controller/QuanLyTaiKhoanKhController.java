package com.example.demo.controller;

import com.example.demo.entity.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class QuanLyTaiKhoanKhController {

    @Autowired
    private HttpSession session;

    @RequestMapping("/qltk-kh")
    public String qltkKh() {
        return "qltk_kh/index";
    }

    @RequestMapping("/qltk-kh/thong-tin")
    public String thongTin(Model model) {
        KhachHang khachHang=(KhachHang) session.getAttribute("khachHangLogin");
        model.addAttribute("khachHang",khachHang);
        return "qltk_kh/thong_tin";
    }

    @RequestMapping("/qltk-kh/doi-mat-khau")
    public String doiMatKhau() {
        return "qltk_kh/doi_mat_khau";
    }
}
