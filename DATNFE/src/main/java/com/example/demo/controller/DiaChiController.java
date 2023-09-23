package com.example.demo.controller;

import com.example.demo.entity.DiaChi;
import com.example.demo.entity.KhachHang;
import com.example.demo.repository.DiaChiRepo;
import com.example.demo.repository.DiachiDao;
import com.example.demo.repository.KhachHangDao;
import com.example.demo.repository.KhachHangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class DiaChiController {

    @Autowired
    DiaChiRepo diaChiRepo;

    @Autowired
    KhachHangRepo khachHangRepo;

    @Autowired
    DiachiDao diachiDao;

    @Autowired
    KhachHangDao khachHangDao;



    //    Phần địa chỉ:
    @GetMapping("/admin/diachi")
    public String index(@ModelAttribute("diachi") DiaChi diaChi, Model model) {
            model.addAttribute("listdanhsach", khachHangRepo.getAll());
        return "khachhang/diachi";
    }

    //   fill danh sách địa chỉ lên table
    @ModelAttribute("listdiachi")
    public List<DiaChi> getall() {
        return diaChiRepo.getall();
    }

//    thêm mới địa chỉ
    @PostMapping("/admin/diachi/save")
    public String save(@ModelAttribute("diachi") DiaChi diaChi) {
        System.out.println(diaChi);
          diachiDao.save(diaChi);
          String ma = diachiDao.findById(diaChi.getId()).get().getKhachHang().getMa(); // lấy mã từ khách hàng để chuyển vào return
        return "redirect:/admin/khachhang/detail/" + ma;
    }


//    xóa địa chỉ theo mã
    @RequestMapping("/admin/diachi/delete/{ma}")
    public String delete(@PathVariable("ma") UUID ma) {
        diaChiRepo.delete(ma);
        return "redirect:/admin/khachhang";
    }


//    detail địa chỉ theo mã khách hàng và mã đia chỉ
    @GetMapping("/admin/diachi/detail")
    public String getbydiachi(@RequestParam(value = "makh",defaultValue = "KH01") String makh, @RequestParam(value = "madc1") String madc1, Model model, @ModelAttribute("diachi") DiaChi diaChi) {
        model.addAttribute("listdanhsach", khachHangRepo.getAll());
        model.addAttribute("diachi", diachiDao.getDiachiByma(madc1));
        model.addAttribute("listdiachi", diachiDao.getdiachibyma(makh));
        model.addAttribute("khachhang", khachHangDao.GetKhachhangByma(makh));
        return "khachhang/detail";
    }

    //      update địa chỉ theo mã địa chỉ
    @PostMapping("/admin/diachi/update/{ma}")
    public String update(@PathVariable("ma") String ma,@ModelAttribute("diachi") DiaChi dc) {
        DiaChi dc1 = diachiDao.getDiachiByma(ma);
        dc1.setHuyen(dc.getHuyen());
        dc1.setXa(dc.getXa());
        dc1.setThanhpho(dc.getThanhpho());
        dc1.setTendiachi(dc.getTendiachi());
        dc1.setTrangthai(dc.getTrangthai());
        diachiDao.save(dc1);
        return "redirect:/admin/khachhang/detail/" + dc1.getKhachHang().getMa();
    }

}
