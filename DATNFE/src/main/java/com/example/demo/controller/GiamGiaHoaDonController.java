package com.example.demo.controller;

import com.example.demo.entity.GiamGiaChiTietHoaDon;
import com.example.demo.entity.GiamGiaHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.GiamGiaChiTietHoaDonRepo;
import com.example.demo.repository.GiamGiaHoaDonRepo;
import com.example.demo.repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GiamGiaHoaDonController {

    @Autowired
    GiamGiaHoaDonRepo giamGiaHoaDonRepo;

    @Autowired
    GiamGiaChiTietHoaDonRepo giamGiaChiTietHoaDonRepo;

    @Autowired
    HoaDonRepo hoaDonRepo;

    //index giảm giá hóa đơn : table page
    @RequestMapping("/admin/giamgiahoadon")
    public String giamGiaHoaDon(@ModelAttribute("giamgiahoadon") GiamGiaHoaDon giamGiaHoaDon,
                                @RequestParam("page") Optional<Integer> page,
                                Model model) {
        PageDTO<GiamGiaHoaDon> pageGGHD = giamGiaHoaDonRepo.getPageGGHD(page.orElse(0));
        model.addAttribute("i", 0);
        model.addAttribute("listPGiamGiaHoaDon", pageGGHD);
        return "giamgiahoadon/giam_gia_hoa_don";
    }

    // create Giảm Giá Hóa Đơn
    @PostMapping("/admin/giamgiahoadon/create")
    public String createGiamGiaHoaDon(@Valid @ModelAttribute("giamgiahoadon") GiamGiaHoaDon giamGiaHoaDon,
                                      BindingResult result,
                                      @RequestParam("page") Optional<Integer> page,
                                      Model model) {

        GiamGiaHoaDon checkma = giamGiaHoaDonRepo.getGiamGiaHoaDonByMa(giamGiaHoaDon.getMa());
        if (result.hasErrors()) {
            PageDTO<GiamGiaHoaDon> pageGGHD = giamGiaHoaDonRepo.getPageGGHD(page.orElse(0));
            model.addAttribute("i", 0);
            model.addAttribute("listPGiamGiaHoaDon", pageGGHD);
            return "giamgiahoadon/giam_gia_hoa_don";
        }
        else if(checkma != null)
        {
            result.rejectValue("ma", "error.giamGiaHoaDon", "Mã đã tồn tại");
            PageDTO<GiamGiaHoaDon> pageGGHD = giamGiaHoaDonRepo.getPageGGHD(page.orElse(0));
            model.addAttribute("i", 0);
            model.addAttribute("listPGiamGiaHoaDon", pageGGHD);
            return "giamgiahoadon/giam_gia_hoa_don";
        }
        giamGiaHoaDonRepo.createGGHD(giamGiaHoaDon);
        return "redirect:/admin/giamgiahoadon";
    }

    // view update Giảm Giá Hóa Đơn
    @RequestMapping("/admin/giamgiahoadon/view-update/{ma}")
    public String viewupdateGiamGiaHoaDon(@PathVariable("ma") String ma, @ModelAttribute("giamgiahoadon") GiamGiaHoaDon giamGiaHoaDon, Model model) {
        model.addAttribute("giamgiahoadon", giamGiaHoaDonRepo.getGiamGiaHoaDonByMa(ma));
        return "giamgiahoadon/update_giam_gia_hoa_don";
    }

    // update Giảm Giá Hóa Đơn
    @PostMapping("/admin/giamgiahoadon/update")
    public String updateGiamGiaHoaDon(@ModelAttribute("giamgiahoadon") GiamGiaHoaDon giamGiaHoaDon) {
        giamGiaHoaDonRepo.createGGHD(giamGiaHoaDon);
        return "redirect:/admin/giamgiahoadon";
    }

    // view detail Giảm Giá Hóa Đơn
    @RequestMapping("/admin/giamgiahoadon/detail/{ma}")
    public String detailGGHD(@PathVariable("ma") String ma, @RequestParam("page") Optional<Integer> page, Model model) {

        // tìm giảm giá hóa đơn theo mã
        model.addAttribute("giamgiahoadon", giamGiaHoaDonRepo.getGiamGiaHoaDonByMa(ma));

        // Tìm danh sách hóa đơn đã được áp mã
//        GiamGiaHoaDon gghdID = giamGiaHoaDonRepo.getGiamGiaHoaDonByMa(ma);
        PageDTO<HoaDon> hoaDonPageDTO = giamGiaHoaDonRepo.getHoaDonByChuongTrinhGiamGiaPage(ma,page.orElse(0));
        model.addAttribute("hoaDonListdaGG", hoaDonPageDTO);
        model.addAttribute("i", 0);
        return "giamgiahoadon/detail_giam_gia_hoa_don";
    }

    // Danh sách Hóa Đơn chưa được giảm giá và trạng thái = 0
    @ModelAttribute("listHoaDon")
    public List<HoaDon> getListHoaDon() {
        return hoaDonRepo.getAllHDchuaGG();
    }

    // Xóa Giảm Giá Hóa Đơn
    @RequestMapping("/admin/giamgiahoadon/delete/{id}")
    public String deleteGiamGiaHoaDon(@PathVariable("id") UUID id) {
        giamGiaHoaDonRepo.deleteGGHD(id);
        return "redirect:/admin/giamgiahoadon";
    }

    // Lọc Giảm Giá Hóa Đơn theo ngày
    @RequestMapping("/admin/giamgiahoadon/loc-theo-ngay")
    public String locGiamGiaHoaDonTheoNgay(
            @RequestParam(value = "ngay_bat_dau", required = false) Date startDate,
            @RequestParam(value = "ngay_ket_thuc", required = false) Date endDate,
            @RequestParam("page") Optional<Integer> page,
            @ModelAttribute("giamgiahoadon") GiamGiaHoaDon giamGiaHoaDon,
            Model model) {

        PageDTO<GiamGiaHoaDon> pageResult = giamGiaHoaDonRepo.getPageGGHDByDateRange(startDate, endDate, page.orElse(0));
        model.addAttribute("i", 0);
        model.addAttribute("listPGiamGiaHoaDon", pageResult);
        return "giamgiahoadon/giam_gia_hoa_don";
    }

    // Lọc Giảm Giá Hóa Đơn theo tên
    @RequestMapping("/admin/giamgiahoadon/loc-theo-ten")
    public String locGiamGiaHoaDonTheoTen(
            @RequestParam(value = "ten", required = false) String ten,
            @RequestParam("page") Optional<Integer> page,
            @ModelAttribute("giamgiahoadon") GiamGiaHoaDon giamGiaHoaDon,
            Model model) {

        PageDTO<GiamGiaHoaDon> pageResult = giamGiaHoaDonRepo.getPageGGHDByTen(ten, page.orElse(0));
        model.addAttribute("i", 0);
        model.addAttribute("listPGiamGiaHoaDon", pageResult);
        return "giamgiahoadon/giam_gia_hoa_don";
    }

    // Lọc GGHD theo trạng thái
    @RequestMapping("/admin/giamgiahoadon/loc-theo-trangthai")
    public String locGiamGiaHoaDonTheoTrangThai(
            @RequestParam(value = "trangthai", required = false) Integer trangthai,
            @RequestParam("page") Optional<Integer> page,
            @ModelAttribute("giamgiahoadon") GiamGiaHoaDon giamGiaHoaDon,
            Model model) {
        PageDTO<GiamGiaHoaDon> pageResult = giamGiaHoaDonRepo.getPageGGHDByTrangThai(trangthai, page.orElse(0));
        model.addAttribute("i", 0);
        model.addAttribute("listPGiamGiaHoaDon", pageResult);
        model.addAttribute("trangthaidetail", trangthai);
        return "giamgiahoadon/giam_gia_hoa_don";
    }

    // Áp mã chương trình giảm giá cho Hóa đơn
    @PostMapping("/admin/giamgiachitiethoadon/createGGCTHD")
    public String createGiamGiaChiTietHoaDon(@RequestParam("HoaDonMa") String HDma, @RequestParam("GiamGiaHoaDonMa") String GGHDma) {
        GiamGiaHoaDon giamGiaHoaDon = giamGiaHoaDonRepo.getGiamGiaHoaDonByMa(GGHDma);
        HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(HDma);
        GiamGiaChiTietHoaDon giamGiaChiTietHoaDon = new GiamGiaChiTietHoaDon();
        giamGiaChiTietHoaDon.setHd(hoaDon);
        giamGiaChiTietHoaDon.setGghd(giamGiaHoaDon);
        giamGiaChiTietHoaDon.setTrangthai(1);
        giamGiaChiTietHoaDonRepo.createGGCTHD2(giamGiaChiTietHoaDon);
        return "redirect:/admin/giamgiahoadon/detail/" + GGHDma;
    }

    // Hủy áp mã chương trình giảm giá cho hóa đơn
    @RequestMapping("/admin/giamgiachitiethoadon/delete/{hdid}/{gghdid}/{gghdma}")
    public String deleteGGCTHD(@PathVariable("hdid") UUID hdid, @PathVariable("gghdid") UUID gghdid, @PathVariable("gghdma") String gghdma) {
        giamGiaChiTietHoaDonRepo.deleteGGCTHD(hdid, gghdid);
        return "redirect:/admin/giamgiahoadon/detail/" + gghdma;
    }

}
