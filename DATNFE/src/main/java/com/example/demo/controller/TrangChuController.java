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
    @RequestMapping("/ctsp/{x}")
    public String ctsp(Model model, @PathVariable("x") String ma) {
        Giay giay = giayDAO.getGiayByMa(ma);
        model.addAttribute("item",giay);
        return "home/chitietsanpham";
    }
    @PostMapping("/cart/add")
    public String addcart(@RequestParam("ma_giay") String ma_giay,@RequestParam("size_giay") String size_giay,@RequestParam("so_luong") Integer so_luong) {
        GiayChiTiet giayChiTiet =  giayChiTietDAO.getAllByMaGiayAndSize(ma_giay,size_giay);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        KhachHang khachHang = khachHangDao.getKhByEmail(username);
        GioHang gioHang = khachHang.getGio_hang();
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setGio_hang(gioHang);
        gioHangChiTiet.setGiay_chi_tiet(giayChiTiet);
        gioHangChiTiet.setSo_luong(so_luong);
        gioHangChiTietDAO.save(gioHangChiTiet);
       return  "home/chitietsanpham";
    }
    @RequestMapping("/cart/view")
    public String viewcart() {
        return  "home/viewcart";
    }
}
