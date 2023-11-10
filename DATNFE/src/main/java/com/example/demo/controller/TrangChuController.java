package com.example.demo.controller;

import com.example.demo.config.Config;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "/login",params = "error")
    public String loginfail() {
        return "layout/login";
    }
    @RequestMapping(value = "/login",params = "logout")
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
        model.addAttribute("items",giayDAO.findAll());
        return "home/index";
    }
    @RequestMapping("/sanpham")
    public String sanpham(Model model) {
        model.addAttribute("items",giayDAO.findAll());
        LocalDate currentDateMinus7Days = LocalDate.now().minusDays(7);
        model.addAttribute("sevenDaysAgo", currentDateMinus7Days);
        return "home/sanpham";
    }


}
