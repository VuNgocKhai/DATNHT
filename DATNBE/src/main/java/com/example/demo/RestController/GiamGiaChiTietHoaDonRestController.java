package com.example.demo.RestController;

import com.example.demo.entity.GiamGiaChiTietHoaDon;
import com.example.demo.entity.GiamGiaHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.GiamGiaChiTietHoaDonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/giamgiachitiethoadon")
public class GiamGiaChiTietHoaDonRestController {

    @Autowired
    GiamGiaChiTietHoaDonDAO giamGiaChiTietHoaDonDAO;

    // Getall Giảm giá chi tiết hóa đơn
    @GetMapping()
    public List<GiamGiaChiTietHoaDon> getListGGCTHD() {
        return giamGiaChiTietHoaDonDAO.findAll();
    }

    //Tìm hóa đơn đã được áp mã
    @GetMapping("/hoandonbygiamgia/{GiamGiaHoaDonId}")
    public List<HoaDon> getHoaDonByChuongTrinhGiamGia(@PathVariable("GiamGiaHoaDonId") UUID GiamGiaHoaDonId) {
        List<HoaDon> hoaDonList = giamGiaChiTietHoaDonDAO.findHoaDonByChuongTrinhGiamGia(GiamGiaHoaDonId);
        return hoaDonList;
    }

    //Tìm hóa đơn đã được áp mã Page
    @GetMapping("/{GiamGiaHoaDonMa}")
    public PageDTO<HoaDon> getHoaDonByChuongTrinhGiamGiaPage(@RequestParam("page") Optional<Integer> page, @PathVariable("GiamGiaHoaDonMa") String GiamGiaHoaDonMa) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return new PageDTO<>(giamGiaChiTietHoaDonDAO.findHoaDonByChuongTrinhGiamGiaPagePage(GiamGiaHoaDonMa,pageable));
    }

    // Tạo mới giảm giá chi tiết hóa đơn ( chức năng này dành cho áp mã)
    @PostMapping("/createGGCTHD")
    public GiamGiaChiTietHoaDon createGGCTHD(@RequestBody GiamGiaChiTietHoaDon giamGiaChiTietHoaDon) {
        return giamGiaChiTietHoaDonDAO.save(giamGiaChiTietHoaDon);
    }

    // Xóa giảm giá chi tiết hóa đơn theo 2 id của hóa đơn và chương trình giảm giá ( chức năng này là hủy áp mã)
    @Transactional
    @RequestMapping("delete/{hdid}/{gghdid}")
    public void deleteGGCTHD(@PathVariable("hdid") UUID HoaDonID, @PathVariable("gghdid") UUID GiamGiaHoaDonID) {
        giamGiaChiTietHoaDonDAO.deleteByHoaDonIdAndGiamGiaHoaDonId(HoaDonID, GiamGiaHoaDonID);
    }
}
