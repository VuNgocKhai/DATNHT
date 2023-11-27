package com.example.demo.controller;

import com.example.demo.entity.GiayChiTiet;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.HoaDonDAO;
import com.example.demo.repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BanHangController {

    @Autowired
    private HoaDonRepo hoaDonRepo;

    @Autowired
    private HoaDonDAO hoaDonDAO;

    //Hiển thị all
    @RequestMapping("/admin/ban-hang")
    public String hienThiAll(@RequestParam("page1") Optional<Integer> page1,
                             @RequestParam("page2") Optional<Integer> page2,
                             @RequestParam("page3") Optional<Integer> page3,
                             @RequestParam("page4") Optional<Integer> page4,
                             @RequestParam("page5") Optional<Integer> page5,
                             @RequestParam("page6") Optional<Integer> page6,
                             Model model) {
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai5 = hoaDonRepo.getPageHDByTrangThai(5, page5.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai6 = hoaDonRepo.getPageHDByTrangThai(6, page6.orElse(0));
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn đang chờ
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn chuẩn bị
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn đang giao
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn hoàn thành
        model.addAttribute("PageHoaDonTT5", hoaDonTrangThai5); // Page hóa đơn đã hủy
        model.addAttribute("PageHoaDonTT6", hoaDonTrangThai6); // Page hóa đơn trả hàng
        return "banhangtaiquay/ban_hang";
    }

    @PostMapping("/admin/ban-hang/xac-nhan-don-hang")
    @Transactional
    public String themGiayVaoHoaDonChiTiet(@RequestParam(value = "selectedMa1", required = false) List<String> selectedMa1,
                                           @RequestParam(value = "selectedMa2", required = false) List<String> selectedMa2,
                                           @RequestParam(value = "selectedMa3", required = false) List<String> selectedMa3,
                                           @RequestParam(value = "huyxacnhan1", required = false, defaultValue = "xac-nhan") String huyxacnhan1,
                                           @RequestParam(value = "huyxacnhan2", required = false, defaultValue = "xac-nhan") String huyxacnhan2,
                                           @RequestParam(value = "huyxacnhan3", required = false, defaultValue = "xac-nhan") String huyxacnhan3,
                                           @RequestParam(value = "giaoHang", required = false, defaultValue = "huy") String giaohang,
                                           @RequestParam(value = "hoanthanh", required = false, defaultValue = "huy") String hoanthanh,
                                           RedirectAttributes redirectAttributes) {

        if (selectedMa1 != null) {
            if ("huy".equals(huyxacnhan1)) {
                for (String maHD : selectedMa1) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(5);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            } else if ("xac-nhan".equals(huyxacnhan1)) {
                for (String maHD : selectedMa1) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    LocalDate currentDate = LocalDate.now();
                    hoaDon.setNgay_thanh_toan(currentDate);
                    hoaDon.setTrangthai(2);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            }
        } else if (selectedMa2 != null) {
            if ("giaohang".equals(giaohang)) {
                for (String maHD : selectedMa2) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(3);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            } else if ("huy".equals(huyxacnhan2)) {
                for (String maHD : selectedMa2) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(5);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            }
        }
        else if (selectedMa3 != null) {
            if ("hoanthanh".equals(hoanthanh)) {
                for (String maHD : selectedMa3) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(4);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            } else if ("huy".equals(huyxacnhan3)) {
                for (String maHD : selectedMa3) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(5);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            }
        }

        return "redirect:/admin/ban-hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don", method = RequestMethod.GET)
    public String timKiemHoaDon(@RequestParam("timTheo") String timTheo,
                                @RequestParam("keyword") String keyword,
                                @RequestParam("trangThai") Integer trangThai,
                                @RequestParam("page1") Optional<Integer> page1,
                                @RequestParam("page2") Optional<Integer> page2,
                                @RequestParam("page3") Optional<Integer> page3,
                                @RequestParam("page4") Optional<Integer> page4,
                                @RequestParam("page5") Optional<Integer> page5,
                                @RequestParam("page6") Optional<Integer> page6,
                                Model model) {

        PageDTO<HoaDon> hoaDonTrangThai = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page1.orElse(0));
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai);
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai5 = hoaDonRepo.getPageHDByTrangThai(5, page5.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai6 = hoaDonRepo.getPageHDByTrangThai(6, page6.orElse(0));
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn chuẩn bị
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn đang giao
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn hoàn thành
        model.addAttribute("PageHoaDonTT5", hoaDonTrangThai5); // Page hóa đơn đã hủy
        model.addAttribute("PageHoaDonTT6", hoaDonTrangThai6); // Page hóa đơn trả hàng
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don2", method = RequestMethod.GET)
    public String timKiemHoaDon1(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 @RequestParam("page5") Optional<Integer> page5,
                                 @RequestParam("page6") Optional<Integer> page6,
                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page2.orElse(0));
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn chuẩn bị
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai5 = hoaDonRepo.getPageHDByTrangThai(5, page5.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai6 = hoaDonRepo.getPageHDByTrangThai(6, page6.orElse(0));
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn đang chờ
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn đang giao
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn hoàn thành
        model.addAttribute("PageHoaDonTT5", hoaDonTrangThai5); // Page hóa đơn đã hủy
        model.addAttribute("PageHoaDonTT6", hoaDonTrangThai6); // Page hóa đơn trả hàng
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don3", method = RequestMethod.GET)
    public String timKiemHoaDon2(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 @RequestParam("page5") Optional<Integer> page5,
                                 @RequestParam("page6") Optional<Integer> page6,
                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page3.orElse(0));
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn đang giao
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai5 = hoaDonRepo.getPageHDByTrangThai(5, page5.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai6 = hoaDonRepo.getPageHDByTrangThai(6, page6.orElse(0));
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn đang chờ
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn chuẩn bị
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn hoàn thành
        model.addAttribute("PageHoaDonTT5", hoaDonTrangThai5); // Page hóa đơn đã hủy
        model.addAttribute("PageHoaDonTT6", hoaDonTrangThai6); // Page hóa đơn trả hàng
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don4", method = RequestMethod.GET)
    public String timKiemHoaDon3(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page5") Optional<Integer> page5,
                                 @RequestParam("page6") Optional<Integer> page6,
                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page4.orElse(0));

        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn hoàn thành
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai5 = hoaDonRepo.getPageHDByTrangThai(5, page5.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai6 = hoaDonRepo.getPageHDByTrangThai(6, page6.orElse(0));
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn đang chờ
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn chuẩn bị
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn đang giao
        model.addAttribute("PageHoaDonTT5", hoaDonTrangThai5); // Page hóa đơn đã hủy
        model.addAttribute("PageHoaDonTT6", hoaDonTrangThai6); // Page hóa đơn trả hàng
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don5", method = RequestMethod.GET)
    public String timKiemHoaDon4(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page5") Optional<Integer> page5,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 @RequestParam("page6") Optional<Integer> page6,

                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai5 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page5.orElse(0));

        model.addAttribute("PageHoaDonTT5", hoaDonTrangThai5); // Page hóa đơn đã hủy
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai6 = hoaDonRepo.getPageHDByTrangThai(6, page6.orElse(0));
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn đang chờ
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn chuẩn bị
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn đang giao
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn hoàn thành
        model.addAttribute("PageHoaDonTT6", hoaDonTrangThai6); // Page hóa đơn trả hàng
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don6", method = RequestMethod.GET)
    public String timKiemHoaDon5(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page6") Optional<Integer> page6,
                                 @RequestParam("page5") Optional<Integer> page5,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 Model model) {
        PageDTO<HoaDon> hoaDonTrangThai6 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page6.orElse(0));

        model.addAttribute("PageHoaDonTT6", hoaDonTrangThai6); // Page hóa đơn trả hàng
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai5 = hoaDonRepo.getPageHDByTrangThai(5, page5.orElse(0));
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn đang chờ
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn chuẩn bị
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn đang giao
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn hoàn thành
        model.addAttribute("PageHoaDonTT5", hoaDonTrangThai5); // Page hóa đơn đã hủy
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }
}