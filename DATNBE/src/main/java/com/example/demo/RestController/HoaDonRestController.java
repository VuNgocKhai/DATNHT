package com.example.demo.RestController;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.HoaDonDAO;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/hoadon")
public class HoaDonRestController {

    @Autowired
    HoaDonDAO hoaDonDAO;

    // Get list hóa đơn
    @GetMapping()
    public List<HoaDon> getListHoaDonchuagg() {
        return hoaDonDAO.findHoaDonChuaApDungChuongTrinhGiamGia();
    }

    // phân trang hóa đơn chưa được áp mã
    @GetMapping("/phantrang")
    public PageDTO<HoaDon> getPageHD(@RequestParam("page1") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(hoaDonDAO.findHoaDonChuaApDungChuongTrinhGiamGiaPage(pageable));
    }

    // phân trang hóa đơn chưa thanh toán
    @GetMapping("/pagehdctt")
    public PageDTO<HoaDon> getPageHDchuaThanhToan(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(hoaDonDAO.findHoaDonChuaThanhToan(pageable));
    }

    // tìm hóa đơn theo mã
    @GetMapping("/{ma}")
    public HoaDon getHoaDonByMa(@PathVariable("ma") String ma) {
        return hoaDonDAO.findHoaDonByMa(ma);
    }

    //tạo mới hóa đơn
    @PostMapping()
    public HoaDon createHoaDon(@RequestBody HoaDon hoaDon) {
        return hoaDonDAO.save(hoaDon);
    }

    //update hóa đơn theo mã
    @PostMapping("/{ma}")
    public HoaDon updateHoaDon(@PathVariable("ma") String ma, @RequestBody HoaDon hoaDon) {
        return hoaDonDAO.save(hoaDon);
    }

    // xóa hóa đơn theo id
    @RequestMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        hoaDonDAO.deleteById(id);
    }

    // lọc giảm giá hóa đơn theo tên
    @GetMapping("/tim-kiem-hoa-don")
    public PageDTO<HoaDon> findHoaDonByMaOrTenKH(
            @RequestParam("keyword") String keyword,
            @RequestParam("page") Optional<Integer> page) {

        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(hoaDonDAO.searchHoaDonByKeyword(keyword, pageable));
    }

}
