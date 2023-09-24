package com.example.demo.controller;

import com.example.demo.entity.CTKMRequestAdd;
import com.example.demo.entity.ChuongTrinhGiamGiaChiTietSP;
import com.example.demo.entity.Giay;
import com.example.demo.repository.ChuongTrinhGiamGiaChiTietSPRepo;
import com.example.demo.repository.ChuongTrinhGiamGiaChitietSanPhamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ChungTrinhGiamGiaChiTietSPController {

    @Autowired
    ChuongTrinhGiamGiaChiTietSPRepo ctkmRepo;

    @Autowired
    ChuongTrinhGiamGiaChitietSanPhamDTO ctkmDTO;

    @ModelAttribute("ctkmTable")
    public List<ChuongTrinhGiamGiaChiTietSP> getListCtkm() {
        return ctkmRepo.getListCtkm();
    }

    @PostMapping("/admin/create-ctkm")
    public String addCTKM(
                          @RequestParam(value = "makhcl") String makhcl,
                          @RequestParam(value = "idGiay") String idGiay
    ) {
        System.out.println(makhcl);
        System.out.println(idGiay);
             return "redirect:/admin/chuong-trinh-giam-gia-sp";
    }

    @GetMapping("/test/demo")
    public String demo(){
        return "giamgiasanpham/test";
    }

    @PostMapping("/test/demo")
    public String demo2(@RequestParam("ten") String ten){
        System.out.println(ten);
        return "giamgiasanpham/test";
    }


}
