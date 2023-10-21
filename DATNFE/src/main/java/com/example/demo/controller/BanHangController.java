package com.example.demo.controller;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.HoaDonDAO;
import com.example.demo.repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don", method = RequestMethod.GET)
    public String timKiemHoaDon(@RequestParam("timTheo") int timTheo,
                                @RequestParam("keyword") String keyword,
                                @RequestParam("trangThai") Integer trangThai,
                                @RequestParam("page2") Optional<Integer> page2,
                                @RequestParam("page3") Optional<Integer> page3,
                                @RequestParam("page4") Optional<Integer> page4,
                                @RequestParam("page5") Optional<Integer> page5,
                                @RequestParam("page6") Optional<Integer> page6,
                                Model model) {

        Page<HoaDon> resultPage = hoaDonDAO.searchHoaDon(keyword, trangThai, PageRequest.of(0, 5)); // Thay PageRequest.of bằng phân trang bạn muốn

        model.addAttribute("PageHoaDonTT1", resultPage); // Page hóa đơn tìm kiếm
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

}
