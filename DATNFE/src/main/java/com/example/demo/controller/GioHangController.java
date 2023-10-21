package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class GioHangController {
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
    GGHDDAO gghddao;

    @RequestMapping("/ctsp/{x}")
    public String ctsp(Model model, @PathVariable("x") String ma) {
        Giay giay = giayDAO.getGiayByMa(ma);
        model.addAttribute("item", giay);
        return "home/chitietsanpham";
    }

    @PostMapping("/cart/add")
    public String addcart(@RequestParam("ma_giay") String ma_giay, @RequestParam("size_giay") String size_giay, @RequestParam("so_luong") Integer so_luong) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        KhachHang khachHang = khachHangDao.getKhByEmail(username);
        GiayChiTiet giayChiTiet = giayChiTietDAO.getAllByMaGiayAndSize(ma_giay, size_giay);
        GioHang gioHang = khachHang.getGio_hang();
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        boolean kq = true;
        System.out.println(giayChiTiet.getId());
        System.out.println(ma_giay);
        System.out.println(size_giay);
        for (GioHangChiTiet x : gioHang.getListGHCT(gioHang.getGioHangChiTiets())
        ) {
            if (x.getGiay_chi_tiet().getId().equals(giayChiTiet.getId())) {
                x.setSo_luong(x.getSo_luong() + so_luong);
                gioHangChiTietDAO.save(x);
                kq = false;
            }
        }
        if (kq == true) {
            gioHangChiTiet.setGio_hang(gioHang);
            gioHangChiTiet.setGiay_chi_tiet(giayChiTiet);
            gioHangChiTiet.setSo_luong(so_luong);
            gioHangChiTiet.setTrangthai(1);
            gioHangChiTietDAO.save(gioHangChiTiet);
        }
        return "redirect:/cart/view";
    }

    @PostMapping("/getlistGiay")
    public String getlistvalue(Model model, HttpServletRequest request, @RequestParam("maVC") String maVC) {
        String maGGHD = "";
        Integer phan_tramGGHD = 0;
        BigDecimal so_tienGGHD = BigDecimal.valueOf(0);
        List<GiamGiaHoaDon> listGGHD = gghddao.findAll();
        for (GiamGiaHoaDon x : listGGHD
        ) {
            if (x.getMa().equals(maVC)) {
                maGGHD = x.getMa();
                phan_tramGGHD = x.getPhan_tram_giam();
                so_tienGGHD = x.getSo_tien_giam();
                model.addAttribute("maGGHD", x.getMa());
                model.addAttribute("phan_tramGGHD", x.getPhan_tram_giam());
            }
        }
        String[] listvalue = request.getParameterValues("listGiay");
        List<UUID> listvalue1 = new ArrayList<>();
        List<GiayChiTiet> giayChiTietList = new ArrayList<>();
        for (String x : listvalue
        ) {
            listvalue1.add(UUID.fromString(x));
            GiayChiTiet giayChiTiet = giayChiTietDAO.findById(UUID.fromString(x)).get();
            System.out.println("gct" + giayChiTiet.getId());
            giayChiTietList.add(giayChiTiet);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        KhachHang khachHang = khachHangDao.getKhByEmail(username);
        List<GioHangChiTiet> gioHangChiTietList = khachHang.getGio_hang().
                getListGHCT(khachHang.getGio_hang().getGioHangChiTiets());
        BigDecimal tongTien = BigDecimal.valueOf(0);
        for (GioHangChiTiet x : gioHangChiTietList
        ) {
            x.setSo_luong(Integer.parseInt(request.getParameter(x.getGiay_chi_tiet().getId() + "soluong")));
            gioHangChiTietDAO.save(x);
        }
        for (GiayChiTiet x : giayChiTietList
        ) {
            tongTien = tongTien.add(x.getGiay().tinhTong(x.getGiay().getGiaban(), Integer.parseInt(request.getParameter(x.getId() + "soluong"))));
        }
        BigDecimal tienGGHD = tongTien.multiply(BigDecimal.valueOf(phan_tramGGHD)).divide(BigDecimal.valueOf(100));
        if (tienGGHD.compareTo(so_tienGGHD) > 0) {
            tienGGHD = so_tienGGHD;
        }
        model.addAttribute("tienGGHD", tienGGHD);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("tienThanhToan", tongTien.subtract(tienGGHD));
        model.addAttribute("listGiay", listvalue1);
        model.addAttribute("maGGHD", maGGHD);
        model.addAttribute("maVC", maVC);
        model.addAttribute("listGHCT", gioHangChiTietList);
        return "home/viewcart";
    }

    @RequestMapping("/cart/view")
    public String viewcart(Model model, HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        KhachHang khachHang = khachHangDao.getKhByEmail(username);
        List<UUID> listUUIDGiay = new ArrayList<>();
        List<GioHangChiTiet> gioHangChiTietList = khachHang.getGio_hang().
                getListGHCT(khachHang.getGio_hang().getGioHangChiTiets());
        BigDecimal tongTien = BigDecimal.valueOf(0);
        for (GioHangChiTiet x : gioHangChiTietList
        ) {
            tongTien = tongTien.add(x.getGiay_chi_tiet().getGiay().tinhTong(x.getGiay_chi_tiet().getGiay().getGiaban(), x.getSo_luong()));
            String a = String.valueOf(x.getGiay_chi_tiet().getId());
            System.out.println(a);
        }
        model.addAttribute("listGiay", listUUIDGiay);
        model.addAttribute("tienGGHD", 0);
        model.addAttribute("maGGHD", "");
        model.addAttribute("tienThanhToan", 0);
        model.addAttribute("tongTien", 0);
        model.addAttribute("listGHCT", gioHangChiTietList);
        return "home/viewcart";
    }

    @PostMapping("/cart/addvoucher")
    public String addVoucher(Model model, @RequestParam("maVC") String maVC) {
        String maGGHD = "";
        Integer phan_tramGGHD = 0;
        BigDecimal so_tienGGHD = BigDecimal.valueOf(0);
        List<GiamGiaHoaDon> listGGHD = gghddao.findAll();
        for (GiamGiaHoaDon x : listGGHD
        ) {
            if (x.getMa().equals(maVC)) {
                maGGHD = x.getMa();
                phan_tramGGHD = x.getPhan_tram_giam();
                so_tienGGHD = x.getSo_tien_giam();
                model.addAttribute("maGGHD", x.getMa());
                model.addAttribute("phan_tramGGHD", x.getPhan_tram_giam());
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        KhachHang khachHang = khachHangDao.getKhByEmail(username);
        List<GioHangChiTiet> gioHangChiTietList = khachHang.getGio_hang().
                getListGHCT(khachHang.getGio_hang().getGioHangChiTiets());
        BigDecimal tongTien = BigDecimal.valueOf(0);
        for (GioHangChiTiet x : gioHangChiTietList
        ) {
            tongTien = tongTien.add(x.getGiay_chi_tiet().getGiay().tinhTong(x.getGiay_chi_tiet().getGiay().getGiaban(), x.getSo_luong()));
        }
        BigDecimal tienGGHD = tongTien.multiply(BigDecimal.valueOf(phan_tramGGHD)).divide(BigDecimal.valueOf(100));
        if (tienGGHD.compareTo(so_tienGGHD) > 0) {
            tienGGHD = so_tienGGHD;
        }
        model.addAttribute("tienGGHD", tienGGHD);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("tienThanhToan", tongTien.subtract(tienGGHD));
        model.addAttribute("listGHCT", gioHangChiTietList);
        System.out.println("tienGGHD la" + tienGGHD);
        return "home/viewcart";
    }

    @RequestMapping("/checkout")
    public String checkout(Model model) {

        return "home/checkout";
    }

    @PostMapping("/checkout")
    public String checkout1(Model model, HttpServletRequest request, @RequestParam("maVC") String maVC) {
        String maGGHD = "";
        Integer phan_tramGGHD = 0;
        BigDecimal so_tienGGHD = BigDecimal.valueOf(0);
        List<GiamGiaHoaDon> listGGHD = gghddao.findAll();
        for (GiamGiaHoaDon x : listGGHD
        ) {
            if (x.getMa().equals(maVC)) {
                maGGHD = x.getMa();
                phan_tramGGHD = x.getPhan_tram_giam();
                so_tienGGHD = x.getSo_tien_giam();
                model.addAttribute("maGGHD", x.getMa());
                model.addAttribute("phan_tramGGHD", x.getPhan_tram_giam());
            }
        }
        String[] listvalue = request.getParameterValues("listGiay");
        List<UUID> listvalue1 = new ArrayList<>();
        List<GiayChiTiet> giayChiTietList = new ArrayList<>();
        for (String x : listvalue
        ) {
            listvalue1.add(UUID.fromString(x));
            GiayChiTiet giayChiTiet = giayChiTietDAO.findById(UUID.fromString(x)).get();
            System.out.println("gct" + giayChiTiet.getId());
            giayChiTietList.add(giayChiTiet);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        KhachHang khachHang = khachHangDao.getKhByEmail(username);
        List<GioHangChiTiet> gioHangChiTietList = khachHang.getGio_hang().
                getListGHCT(khachHang.getGio_hang().getGioHangChiTiets());
        BigDecimal tongTien = BigDecimal.valueOf(0);
        for (GiayChiTiet x : giayChiTietList
        ) {
            tongTien = tongTien.add(x.getGiay().tinhTong(x.getGiay().getGiaban(), Integer.parseInt(request.getParameter(x.getId() + "soluong"))));
        }
        BigDecimal tienGGHD = tongTien.multiply(BigDecimal.valueOf(phan_tramGGHD)).divide(BigDecimal.valueOf(100));
        if (tienGGHD.compareTo(so_tienGGHD) > 0) {
            tienGGHD = so_tienGGHD;
        }
        model.addAttribute("tienGGHD", tienGGHD);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("tienThanhToan", tongTien.subtract(tienGGHD));
        model.addAttribute("listGiay", listvalue1);
        model.addAttribute("maGGHD", maGGHD);
        model.addAttribute("maVC", maVC);
        model.addAttribute("listGHCT", gioHangChiTietList);
        return "home/checkout";
    }
}
