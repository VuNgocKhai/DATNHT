package com.example.demo.RestController;

import com.example.demo.entity.GiamGiaChiTietHoaDon;
import com.example.demo.entity.GiamGiaHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.GiamGiaChiTietHoaDonDAO;
import com.example.demo.repository.GiamGiaHoaDonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/giamgiahoadon")
public class GiamGiaHoaDonRestController {

    @Autowired
    GiamGiaHoaDonDAO giamGiaHoaDonDAO;

    @Autowired
    GiamGiaChiTietHoaDonDAO giamGiaChiTietHoaDonDAO;

    // Lấy list giảm giá hóa đơn
    @GetMapping()
    public List<GiamGiaHoaDon> getListGiamGiaHoaDon() {
        return giamGiaHoaDonDAO.findAll();
    }

    // Phân trang giảm giá hóa đơn
    @GetMapping("/phantrang")
    public PageDTO<GiamGiaHoaDon> getPageGGHD(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(giamGiaHoaDonDAO.findAll(pageable));
    }

    // Tạo mới giảm giá hóa đơn
    @PostMapping()
    public GiamGiaHoaDon createGGHD(@RequestBody GiamGiaHoaDon giamGiaHoaDon) {
        return giamGiaHoaDonDAO.save(giamGiaHoaDon);
    }

    // Tìm giảm giá hóa đơn theo mã
    @GetMapping("/{ma}")
    public GiamGiaHoaDon getGiamGiaHoaDonByMa(@PathVariable("ma") String ma) {
        return giamGiaHoaDonDAO.findGiamGiaHoaDonByMa(ma);
    }

    //update giảm giá hóa đơn theo mã
    @PostMapping("/{ma}")
    public GiamGiaHoaDon updateGiamGiaHoaDon(@PathVariable("ma") String ma, @RequestBody GiamGiaHoaDon giamGiaHoaDon) {
        return giamGiaHoaDonDAO.save(giamGiaHoaDon);
    }

    // Xóa giảm giá hóa đơn theo id
    @RequestMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        giamGiaHoaDonDAO.deleteById(id);
    }

    // lọc giảm giá hóa đơn theo ngày
    @GetMapping("/loc-theo-ngay")
    public PageDTO<GiamGiaHoaDon> locGiamGiaHoaDonTheoNgay(
            @RequestParam("ngay_bat_dau") Date startDate,
            @RequestParam("ngay_ket_thuc") Date endDate,
            @RequestParam("page") Optional<Integer> page) {

        if (startDate == null) {
            startDate = java.sql.Date.valueOf("1900-01-01");
        }
        if (endDate == null) {
            endDate = java.sql.Date.valueOf("2099-12-12");
        }

        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(giamGiaHoaDonDAO.findGiamGiaHoaDonByNgayBatDauAndNgayKetThucBetween(startDate, endDate, pageable));
    }

    // lọc giảm giá hóa đơn theo tên
    @GetMapping("/loc-theo-ten")
    public PageDTO<GiamGiaHoaDon> locGiamGiaHoaDonTheoTen(
            @RequestParam("ten") String ten,
            @RequestParam("page") Optional<Integer> page) {

        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(giamGiaHoaDonDAO.findGiamGiaHoaDonByTen(ten, pageable));
    }

    // lọc giảm giá hóa đơn theo trạng thái
    @GetMapping("/loc-theo-trangthai")
    public PageDTO<GiamGiaHoaDon> locGiamGiaHoaDonTheoTrangThai(
            @RequestParam("trangthai") Integer trangthai,
            @RequestParam("page") Optional<Integer> page) {

        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(giamGiaHoaDonDAO.findGiamGiaHoaDonByTrangthai(trangthai, pageable));
    }

    //Tìm hóa đơn đã được áp mã Page
    @GetMapping("/phantrang/{GiamGiaHoaDonMa}")
    public PageDTO<HoaDon> getHoaDonByChuongTrinhGiamGiaPage(@RequestParam("page") Optional<Integer> page, @PathVariable("GiamGiaHoaDonMa") String GiamGiaHoaDonMa) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(giamGiaChiTietHoaDonDAO.findHoaDonByChuongTrinhGiamGiaPagePage(GiamGiaHoaDonMa,pageable));
    }

}
