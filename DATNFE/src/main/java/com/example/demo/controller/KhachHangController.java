package com.example.demo.controller;

import com.example.demo.entity.DiaChi;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.DiaChiRepo;
import com.example.demo.repository.DiachiDao;
import com.example.demo.repository.KhachHangDao;
import com.example.demo.repository.KhachHangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class KhachHangController {
    @Autowired
    KhachHangRepo khachHangRepo;

    @Autowired
    DiachiDao diachiDao;

    @Autowired
    DiaChiRepo diaChiRepo;

    @Autowired
    KhachHangDao khachHangDao;

// Phần khách hàng

//    List khách hàng và phân trang và tìm kiếm
    @RequestMapping("/admin/khachhang")
    public String index(@ModelAttribute("khachhang") KhachHang khachHang
                        ,@RequestParam("page") Optional<Integer> pageNumber , Model model
                        ,@RequestParam("keyword") Optional<String> keyword) {
        PageDTO<KhachHang> page1 = khachHangRepo.searchAndPaginate(pageNumber.orElse(0),"%" +keyword.orElse("") + "%");
        model.addAttribute("i", 0);
        model.addAttribute("listPKhachhang", page1);
        model.addAttribute("keyword", keyword.orElse(""));
        return "khachhang/Khach_hang";
    }

//    fill listdanhsach khách hàng lên table
    @ModelAttribute("listdanhsach")
    public List<KhachHang> getall() {
        return khachHangRepo.getAll();
    }


    @RequestMapping("/admin/khachhang/detail")
    public String detail(@ModelAttribute("khachhang") KhachHang khachHang) {
        return "khachhang/detail";
    }

//    detail khách hàng theo mã
    @GetMapping("/admin/khachhang/detail/{ma}")
    public String getBykhachhangma(@PathVariable("ma") String ma,
                                   Model model,
                                   @ModelAttribute("diachi") DiaChi diaChi) {
        model.addAttribute("listdiachi", diachiDao.getAllByMaDiaChi(ma));
        model.addAttribute("khachhang", khachHangDao.GetKhachhangByma(ma));
        return "khachhang/detail";
    }


//    Thêm mới  khách hàng
    @PostMapping("admin/khachhang/save")
    public String save(@ModelAttribute("khachhang") KhachHang kh) {
        String ma = khachHangDao.save(kh).getMa();
        return "redirect:/admin/khachhang/detail/" + ma;
    }

    //    Xóa khách hàng
    @RequestMapping("/admin/khachhang/delete/{ma}")
    public String delete(@PathVariable("ma") UUID ma) {
        khachHangRepo.delete(ma);
        return "redirect:/admin/khachhang";
    }

    //      update Khách Hàng theo mã khách hàng
    @PostMapping("/admin/khachhang/update/{id}")
    public String update(@PathVariable("id") String id,@ModelAttribute("khachhang") KhachHang kh) {
        khachHangRepo.update(id, kh);
        return "redirect:/admin/khachhang/detail/" + id;
    }



}
