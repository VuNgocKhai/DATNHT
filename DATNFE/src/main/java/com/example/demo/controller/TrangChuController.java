package com.example.demo.controller;

import com.example.demo.config.Config;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public String login() {
        return "layout/login";
    }

    @RequestMapping(value = "/login", params = "error")
    public String loginfail() {
        return "layout/login";
    }

    @RequestMapping(value = "/login", params = "logout")
    public String logout() {
        return "layout/logout";
    }
    @RequestMapping("/oauth2/login/success")
    public String oauth2(OAuth2AuthenticationToken oauth2){
        userService.loginFromOAuth2(oauth2);
        return "home/index";
    }
    @RequestMapping("/trangchu")
    public String trangchu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());
        if (khachHang == null) {
            model.addAttribute("items", giayDAO.findAll());
        }else {
            model.addAttribute("khachHang",khachHang);
            model.addAttribute("items", giayDAO.findAll());
        }

        return "home/index";
    }
    @Data
    public static class SearchForm {
        String tensp="";
        String thuong_hieu="";
        String chat_lieu="";
        String xuat_xu="";
        String mau_sac="";
        String gioi_tinh="";
        String kieu_dang="";
        String de_giay="";
        String kich_co="";
        Integer page = 0;
        BigDecimal tien_min;
        BigDecimal tien_max;
    }
    @RequestMapping("/sanpham")
    public String sanpham(Model model,@ModelAttribute("searchform") SearchForm searchForm) {
        System.out.println("Thuong hieu la"+searchForm.thuong_hieu);
        List<GiayChiTiet> giayChiTietList;
        if (searchForm.tien_min==null && searchForm.tien_max==null){
            giayChiTietList = giayChiTietDAO.getSearchsanphamByTT("%"+searchForm.tensp+"%",
                    BigDecimal.ZERO,BigDecimal.valueOf(999999999),
                    "%"+searchForm.thuong_hieu+"%","%"+searchForm.kich_co+"%","%"+searchForm.chat_lieu+"%","%"+searchForm.xuat_xu+"%",
                    "%"+searchForm.mau_sac+"%","%"+searchForm.gioi_tinh+"%","%"+searchForm.kieu_dang+"%","%"+searchForm.de_giay+"%");
        }
        if (searchForm.tien_min==null){
            giayChiTietList = giayChiTietDAO.getSearchsanphamByTT("%"+searchForm.tensp+"%",
                    BigDecimal.ZERO,searchForm.tien_max,
                    "%"+searchForm.thuong_hieu+"%","%"+searchForm.kich_co+"%","%"+searchForm.chat_lieu+"%","%"+searchForm.xuat_xu+"%",
                    "%"+searchForm.mau_sac+"%","%"+searchForm.gioi_tinh+"%","%"+searchForm.kieu_dang+"%","%"+searchForm.de_giay+"%");
        }
        if(searchForm.tien_max==null){
            giayChiTietList = giayChiTietDAO.getSearchsanphamByTT("%"+searchForm.tensp+"%",
                    searchForm.tien_min,BigDecimal.valueOf(999999999),
                    "%"+searchForm.thuong_hieu+"%","%"+searchForm.kich_co+"%","%"+searchForm.chat_lieu+"%","%"+searchForm.xuat_xu+"%",
                    "%"+searchForm.mau_sac+"%","%"+searchForm.gioi_tinh+"%","%"+searchForm.kieu_dang+"%","%"+searchForm.de_giay+"%");

        }else {
            giayChiTietList = giayChiTietDAO.getSearchsanphamByTT("%"+searchForm.tensp+"%",
                    searchForm.tien_min,searchForm.tien_max,
                    "%"+searchForm.thuong_hieu+"%","%"+searchForm.kich_co+"%","%"+searchForm.chat_lieu+"%","%"+searchForm.xuat_xu+"%",
                    "%"+searchForm.mau_sac+"%","%"+searchForm.gioi_tinh+"%","%"+searchForm.kieu_dang+"%","%"+searchForm.de_giay+"%");
        }
        Pageable pageable= PageRequest.of(searchForm.page,20);
        List<Giay> giayList = new ArrayList<>();
        System.out.println("Mang là"+giayChiTietList.size());

        for (GiayChiTiet x:giayChiTietList
             ) {
            if (giayList.isEmpty()){
                giayList.add(x.getGiay());
            }else {
                boolean kq =true;
                for (Giay a:giayList
                     ) {
                    if (x.getGiay().getMa().equals(a.getMa())){
                        kq=false;
                    }
                }
                if (kq==true){
                    giayList.add(x.getGiay());
                }
            }
        }
        model.addAttribute("items", giayList);
        return "home/sanpham";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());
        model.addAttribute("khachHang",khachHang);
        return "home/contact";
    }

    @PostMapping("/contact/send-your-opininon")
    public String guiphanhoi(@RequestParam("ten") String ten,
                             @RequestParam("email")
                             @Email(message = "Địa chỉ Email không hợp lệ") String email,
                             @RequestParam("ykienphanhoi") String ykienphanhoi,
                             @RequestParam("agree") Integer agree) {
        if (ten != null && email != null && ykienphanhoi != null && agree != null)
        {
            System.out.println(ten + email + ykienphanhoi + agree);
        }
        return "redirect:/contact";
    }

}
