package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TrangChuController {
    @Autowired
    GiayDAO giayDAO;
    @Autowired
    GiayChiTietDAO giayChiTietDAO;
    @Autowired
    GioHangDAO gioHangDAO;
    @Autowired
    GioHangChiTietDAO gioHangChiTietDAO;
    @Autowired
    KhachHangDao khachHangDao;
    @RequestMapping("/login")
    public String login() {
        return "layout/login";
    }
    @RequestMapping("/trangchu")
    public String trangchu(Model model) {
        model.addAttribute("items",giayDAO.findAll());
        return "home/index";
    }
    @RequestMapping("/sanpham")
    public String sanpham(Model model) {
        model.addAttribute("items",giayDAO.findAll());
        return "home/sanpham";
    }

}
