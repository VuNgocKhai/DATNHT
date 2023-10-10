package com.example.demo.interceptor;

import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalInterceptor
        implements HandlerInterceptor {

    @Autowired
    CamGiacRepo camGiacRepo;
    @Autowired
    ChatLieuRepo chatLieuRepo;
    @Autowired
    DanhMucRepo danhMucRepo;
    @Autowired
    DeGiayRepo deGiayRepo;
    @Autowired
    DiaHinhRepo diaHinhRepo;
    @Autowired
    DoCaoGiayRepo doCaoGiayRepo;
    @Autowired
    GioiTinhRepo gioiTinhRepo;
    @Autowired
    KichCoRepo kichCoRepo;
    @Autowired
    MauSacRepo mauSacRepo;
    @Autowired
    ThoiTietThichHopRepo thoiTietThichHopRepo;
    @Autowired
    ThuongHieuRepo thuongHieuRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("HElô");
        request.setAttribute("listCamGiac", camGiacRepo.getListCamGiac());
        request.setAttribute("listChatLieu", chatLieuRepo.getListChatLieu());
        request.setAttribute("listDanhMuc", danhMucRepo.getListDanhMuc());
        request.setAttribute("listDeGiay", deGiayRepo.getListDeGiay());
        request.setAttribute("listDiaHinh", diaHinhRepo.getListDiaHinh());
        request.setAttribute("listDoCaoGiay", doCaoGiayRepo.getListDoCaoGiay());
        request.setAttribute("listGioiTinh", gioiTinhRepo.getListGioiTinh());
        request.setAttribute("listKichCo", kichCoRepo.getListKichCo());
        request.setAttribute("listMauSac", mauSacRepo.getListMauSac());
        request.setAttribute("listThoiTietThichHop", thoiTietThichHopRepo.getListThoiTietThichHop());
        request.setAttribute("listThuongHieu", thuongHieuRepo.getListThuongHieu());
    }
}
