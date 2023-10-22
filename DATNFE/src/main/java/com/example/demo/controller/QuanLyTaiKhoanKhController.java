package com.example.demo.controller;

import com.example.demo.entity.DiaChi;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
public class QuanLyTaiKhoanKhController {

    @Autowired
    private KhachHangRepo khachHangRepo;

    @Autowired
    private DiaChiRepo diaChiRepo;

    @Autowired
    private HoaDonDAO hoaDonDAO;

    @Autowired
    private DiachiDao diachiDao;

    @Autowired
    private KhachHangDao khachHangDao;


    private Authentication authentication;

    @RequestMapping("/qltk-kh")
    public String qltkKh() {
        return "qltk_kh/index";
    }

    @RequestMapping("/qltk-kh/thong-tin")
    public String thongTin(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());
        model.addAttribute("khachHang", khachHang);
        return "qltk_kh/thong_tin";
    }

    @PostMapping("/qltk-kh/cap-nhat-thong-tin/{idKh}")
    public String capNhatThongTin(@PathVariable String idKh, @ModelAttribute KhachHang khachHang) {
        KhachHang kh = khachHangDao.findById(UUID.fromString(idKh)).get();
        khachHang.setMatkhau(kh.getMatkhau());
        khachHang.setTrangthai(kh.getTrangthai());
        khachHang.setEmail(kh.getEmail());
        khachHangRepo.update(idKh, khachHang);
        return "redirect:/qltk-kh/thong-tin";
    }

    @RequestMapping("/qltk-kh/doi-mat-khau")
    public String doiMatKhau(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());
        model.addAttribute("khachHang", khachHang);
        return "qltk_kh/doi_mat_khau";
    }

    @PostMapping("/qltk-kh/doi-mat-khau")
    public String doiMatKhauPost(@RequestParam String mkCu,
                                 @RequestParam String mkMoi,
                                 @RequestParam String mkMoi2
    ) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());
        if (mkCu.trim().equalsIgnoreCase(khachHang.getMatkhau())) {
            if (mkMoi.trim().equalsIgnoreCase(mkMoi2.trim())) {
                khachHang.setMatkhau(mkMoi);
                khachHangRepo.update(String.valueOf(khachHang.getId()), khachHang);
            }
        }
        return "redirect:/qltk-kh/doi-mat-khau";
    }

    @RequestMapping("/qltk-kh/dia-chi")
    public String diaChiKh(Model model, @ModelAttribute DiaChi diaChi) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());
        diaChi.setKhachHang(khachHang);
        model.addAttribute("listdiachi", diachiDao.getAllByMaDiaChi(khachHang.getMa()));
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("diaChiUd", new DiaChi());
        return "qltk_kh/dia_chi";
    }

    @RequestMapping("/qltk-kh/xoa-dia-chi/{ma}")
    public String xoaDiaChi(@PathVariable UUID ma) {
        diaChiRepo.delete(ma);
        return "redirect:/qltk-kh/dia-chi";
    }

    @PostMapping("/qltk-kh/them-dia-chi")
    public String themDiaChi(@ModelAttribute DiaChi diaChi) {
        if(diaChi.getTrangthai()!=null){
            if(diaChi.getTrangthai()==1){
                diachiDao.updateTtDiaChiByIdKh(0,diaChi.getKhachHang().getId());
            }
        }
        diachiDao.save(diaChi);
        return "redirect:/qltk-kh/dia-chi";
    }

    @PostMapping("/qltk-kh/cap-nhat-dia-chi")
    public String capNhatDiaChi(@ModelAttribute("diaChiUd") DiaChi diaChi) {
        DiaChi dc1 = diachiDao.getDiachiByma(diaChi.getMadc());
        dc1.setHuyen(diaChi.getHuyen());
        dc1.setXa(diaChi.getXa());
        dc1.setThanhpho(diaChi.getThanhpho());
        dc1.setTendiachi(diaChi.getTendiachi());
        dc1.setTrangthai(diaChi.getTrangthai());
        dc1.setTen_nguoi_nhan(diaChi.getTen_nguoi_nhan());
        dc1.setSdt_nguoi_nhan(diaChi.getSdt_nguoi_nhan());
        if(diaChi.getTrangthai()!=null){
            if(diaChi.getTrangthai()==1){
                diachiDao.updateTtDiaChiByIdKh(0,diaChi.getKhachHang().getId());
            }
        }
        diachiDao.save(dc1);
        return "redirect:/qltk-kh/dia-chi";
    }

    @GetMapping("/qltk-kh/dat-dc-mac-dinh/{ma}")
    public String datDcMacDinh(@PathVariable("ma") String madc) {
        DiaChi dc=diachiDao.getDiachiByma(madc);
        diachiDao.updateTtDiaChiByIdKh(0,dc.getKhachHang().getId());
        diachiDao.updateTtDiaChiByMaDc(1,madc);
        return "redirect:/qltk-kh/dia-chi";
    }

    static Map<String, Integer> trangThaiDonHang;
    static {
        trangThaiDonHang = new HashMap<String, Integer>();
        trangThaiDonHang.put("tat-ca", null);
        trangThaiDonHang.put("cho-xac-nhan", 1);
        trangThaiDonHang.put("dang-chuan-bi", 2);
        trangThaiDonHang.put("dang-giao-hang", 3);
        trangThaiDonHang.put("hoan-thanh", 4);
        trangThaiDonHang.put("da-huy", 5);
        trangThaiDonHang.put("tra-hang-hoan-tien", 6);
    }

    @GetMapping("/qltk-kh/don-hang")
    public String donHang(Model model,
                          @RequestParam(defaultValue = "tat-ca") String trangThaiDon,
                          @RequestParam(defaultValue = "0") String number
                          ) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());
        Pageable pageable= PageRequest.of(Integer.valueOf(number),5);
        Page<HoaDon>page=hoaDonDAO.findHdByMaKhAndTt(khachHang.getMa(),trangThaiDonHang.get(trangThaiDon),pageable);
        System.out.println(trangThaiDon+trangThaiDonHang.get(trangThaiDon));
        PageDTO<HoaDon> pageDTO=new PageDTO<>(page);
        model.addAttribute("pageHd",pageDTO);
        model.addAttribute("trangThaiDonHang",trangThaiDonHang);
        model.addAttribute("trangThaiDon",trangThaiDon);
        model.addAttribute("khachHang", khachHang);
        return "qltk_kh/don_hang";
    }



}
