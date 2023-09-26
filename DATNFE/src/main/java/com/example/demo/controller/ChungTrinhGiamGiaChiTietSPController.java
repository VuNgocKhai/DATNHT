package com.example.demo.controller;

import com.example.demo.entity.CTKMRequestAdd;
import com.example.demo.entity.ChuongTrinhGiamGiaChiTietSP;
import com.example.demo.entity.ChuongTrinhGiamGiaSP;
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
    public String addCTKM(CTKMRequestAdd addRequest,
                          @RequestParam(value = "idKM") UUID idKM,
                          @RequestParam(value = "idGiay") UUID idGiay,
//                          @RequestParam(value = "idKM") String idKM,
//                          @RequestParam(value = "idGiay") String idGiay,
                          @RequestParam(value = "giaBan") String giaBan,
                          @RequestParam(value = "phanTramGiam") String phanTramGiam
    ) {
        //System.out.println(idKM);


        Integer giaBanInt = Integer.parseInt(giaBan);
        Integer ptGiamInt = Integer.parseInt(phanTramGiam);
        BigDecimal soTienDaGiam = BigDecimal.valueOf(giaBanInt * ptGiamInt);
        ChuongTrinhGiamGiaChiTietSP ctkm = new ChuongTrinhGiamGiaChiTietSP();
        ctkm.setGiay(Giay.builder().idGiay(idGiay).build());
        ctkm.setChuongTrinhGiamGiaSP(ChuongTrinhGiamGiaSP.builder().idKhuyenMai(idKM).build());
        ctkm.setTrangThai(1);
        ctkm.setSoTienDaGiam(soTienDaGiam);
        ctkmDTO.save(ctkm);
        return "redirect:/admin/chuong-trinh-giam-gia-sp";
    }

    public ChuongTrinhGiamGiaChiTietSP addkm(CTKMRequestAdd addRequest) {
        ChuongTrinhGiamGiaChiTietSP add = addRequest.dtoCTKM(new ChuongTrinhGiamGiaChiTietSP());
        return ctkmDTO.save(add);
    }

    @GetMapping("/test/demo")
    public String demo() {
        return "giamgiasanpham/test";
    }

    @PostMapping("/test/demo")
    public String demo2(@RequestParam("ten") String ten) {
        System.out.println(ten);
        return "giamgiasanpham/test";
    }


}
