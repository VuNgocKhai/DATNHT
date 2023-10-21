package com.example.demo.controller;

import com.example.demo.entity.ChuongTrinhGiamGiaChiTietSP;
import com.example.demo.entity.ChuongTrinhGiamGiaSP;
import com.example.demo.entity.Giay;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ChuongTrinhGiamGiaChiTietSPRepo;
import com.example.demo.repository.ChuongTrinhGiamGiaChitietSanPhamDTO;
import com.example.demo.repository.ChuongTrinhGiamGiaSPRepo;
import com.example.demo.repository.GiayDTO;
import com.example.demo.repository.GiayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ChuongTrinhGiamGiaSPController {

    @Autowired
    ChuongTrinhGiamGiaSPRepo repo;

    @Autowired
    GiayRepo giayRepo;

    @Autowired
    ChuongTrinhGiamGiaChiTietSPRepo ctkmRepo;

    @Autowired
    ChuongTrinhGiamGiaChitietSanPhamDTO ctkmDTO;

    @Autowired
    GiayDTO giayDTO;

    ///////////         CHUONG TRINH GIAM GIA SAN PHAM          ///////////////
//    @RequestMapping("/admin/chuong-trinh-giam-gia-sp")
//    public String voucher(@ModelAttribute("voucherForm") ChuongTrinhGiamGiaSP chuongTrinhGiamGiaSP,
//                          @RequestParam("page") Optional<Integer> pageNumber, Model model,
//                          @RequestParam("keyword") Optional<String> keyword) {
//        PageDTO<ChuongTrinhGiamGiaSP> pageNo = repo.searchAndPaginate(pageNumber.orElse(0), "%" + keyword.orElse("") + "%");
////        PageDTO<ChuongTrinhGiamGiaSP> pageS = repo.searchTrangThai(pageNumber.orElse(0), "%" + keyword.orElse("") + "%");
//        model.addAttribute("i", 0);
//        model.addAttribute("voucherTable", pageNo);
////        model.addAttribute("voucherTable", pageS);
//        model.addAttribute("keyword", keyword.orElse(""));
//        model.addAttribute("voucherForm", new ChuongTrinhGiamGiaSP());
//        return "giamgiasanpham/chuong_trinh_giam_gia_san_pham";
//    }

    @RequestMapping("/admin/chuong-trinh-giam-gia-sp")
    public String voucher(@ModelAttribute("voucherForm") ChuongTrinhGiamGiaSP chuongTrinhGiamGiaSP,
                          @RequestParam("page") Optional<Integer> pageNumber, Model model,
                          @RequestParam("keyword") Optional<String> keyword) {
        PageDTO<ChuongTrinhGiamGiaSP> pageNo = repo.searchTrangThaiAndKeyword(pageNumber.orElse(0), "%" + keyword.orElse("") + "%");
        model.addAttribute("i", 0);
        model.addAttribute("voucherTable", pageNo);
        model.addAttribute("keyword", keyword.orElse(""));
        model.addAttribute("voucherForm", new ChuongTrinhGiamGiaSP());
        return "giamgiasanpham/chuong_trinh_giam_gia_san_pham";
    }

    @ModelAttribute("voucherTable")
    public List<ChuongTrinhGiamGiaSP> getListVoucher() {
        return repo.getListVoucher();
    }

    @PostMapping("/admin/chuong-trinh-giam-gia-sp/create")
    public String createVoucher(@ModelAttribute("voucherForm") ChuongTrinhGiamGiaSP voucherForm) {
        repo.saveVoucher(voucherForm);
        return "redirect:/admin/chuong-trinh-giam-gia-sp";
    }

    @GetMapping("/admin/chuong-trinh-giam-gia-sp/viewupdate/{id}")
    public String viewUpdate(@ModelAttribute("voucherForm") ChuongTrinhGiamGiaSP chuongTrinhGiamGiaSP,
                             @PathVariable("id") UUID id, Model model) {
        model.addAttribute("voucherForm", new ChuongTrinhGiamGiaSP());
        model.addAttribute("voucherForm", repo.getOneById(id));
        return "giamgiasanpham/update-khuyen-mai-san-pham";
    }

    @GetMapping("/admin/chuong-trinh-giam-gia-sp/viewadd")
    public String viewAdd(@ModelAttribute("voucherForm") ChuongTrinhGiamGiaSP chuongTrinhGiamGiaSP, Model model) {
        model.addAttribute("voucherForm", new ChuongTrinhGiamGiaSP());
        return "giamgiasanpham/add-khuyen-mai-san-pham";
    }

    @PostMapping("/admin/chuong-trinh-giam-gia-sp/update/{id}")
    public String update(@PathVariable("id") UUID id, @ModelAttribute("voucherForm") ChuongTrinhGiamGiaSP voucherForm) {
        repo.update(id, voucherForm);
        return "redirect:/admin/chuong-trinh-giam-gia-sp";
    }

    @PostMapping("/admin/chuong-trinh-giam-gia-sp/add")
    public String add(@ModelAttribute("voucherForm") ChuongTrinhGiamGiaSP voucherForm) {
        repo.saveVoucher(voucherForm);
        return "redirect:/admin/chuong-trinh-giam-gia-sp";
    }
///////////          END          ///////////////

    //////////      chi-tiet-khuyen-mai     ////
    @RequestMapping("/admin/chuong-trinh-giam-gia-sp/detail/{idVoucher}")
    public String detailVoucher(@ModelAttribute("ctkmForm") ChuongTrinhGiamGiaChiTietSP ctggctsp,
                                @PathVariable("idVoucher") UUID idVoucher, Model model) {
        model.addAttribute("voucherForm", repo.getOneById(idVoucher));
        model.addAttribute("giayCheckBox", giayRepo.getListGiay());     //ListAllGiay
        List<ChuongTrinhGiamGiaChiTietSP> dsIdSanPham = ctkmDTO.listGiayByIdKM(idVoucher);
        model.addAttribute("dsSPTable", dsIdSanPham);
        model.addAttribute("listGiay", giayDTO.listGiayChuaApDung());

        return "giamgiasanpham/chi_tiet_khuyen_mai";
    }

    @GetMapping("/admin/ctkm/delete/{idKhuyenMai}/{idGiay}")
    public String deleteby2Id(@PathVariable("idKhuyenMai") UUID idKM,
                              @PathVariable("idGiay") UUID idGiay,
                              Model model) {
        ChuongTrinhGiamGiaChiTietSP delCTKM = ctkmDTO.selectByTwoId(idKM, idGiay);
        ctkmDTO.deleteById(delCTKM.getId());
        List<ChuongTrinhGiamGiaChiTietSP> dsIdSanPham = ctkmDTO.listGiayByIdKM(idKM);
        model.addAttribute("dsSPTable", dsIdSanPham);

        return "redirect:/admin/chuong-trinh-giam-gia-sp/detail/" +idKM;
    }

    ////////////////////////////////////////////

    @ModelAttribute("giayCheckBox")
    public List<Giay> getListGiay() {
        return giayRepo.getListGiay();
    }

    @ModelAttribute("ctkmDetail")
    public List<ChuongTrinhGiamGiaChiTietSP> getListCtkm() {
        return ctkmRepo.getListCtkm();
    }

    @GetMapping("/view-ctkm")
    public String viewKhuyenMai(Model model) {
        model.addAttribute("ctkmTable", ctkmRepo.getListCtkm());
        //return "giamgiasanpham/chuong_tring_giam_gia_san_pham";
        return "giamgiasanpham/chi_tiet_khuyen_mai";
    }



}