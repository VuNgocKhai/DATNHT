package com.example.demo.RestController;

import com.example.demo.entity.ChuongTrinhGiamGiaSP;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ChuongTrinhGiamGiaSPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/chuong-trinh-giam-gia-sp")
public class ChuongTrinhGiamGiaSPRestController {

    @Autowired
    private ChuongTrinhGiamGiaSPRepository chuongTrinhGiamGiaSPRepository;

    @GetMapping()        // http://localhost:2020/rest/chuong-trinh-giam-gia-sp
    public ResponseEntity<?> getAllVoucher() {
        return ResponseEntity.ok(chuongTrinhGiamGiaSPRepository.findAll());
    }


//    /SOURCE CODE ĐÚNG
//    @GetMapping("/phantrang")
//    public PageDTO<ChuongTrinhGiamGiaSP> getPageGiamGiaSP(@RequestParam("page") Optional<Integer> page,
//                                                         @RequestParam("keyword") Optional<String> keyword) {
//        Pageable pageable = PageRequest.of(page.orElse(0), 5);
//        Page<ChuongTrinhGiamGiaSP> giamGiaSPPage;
//        if (keyword != null) {
//            giamGiaSPPage = (Page<ChuongTrinhGiamGiaSP>) chuongTrinhGiamGiaSPRepository.timKiemMaHoacTen("%" +keyword.get() + "%", pageable);
//        } else {
//            giamGiaSPPage = chuongTrinhGiamGiaSPRepository.findAll(pageable);
//        }
//
//        return new PageDTO<>(giamGiaSPPage);
//
//    }

    @GetMapping("/phantrang")
    public PageDTO<ChuongTrinhGiamGiaSP> getPageGiamGiaSP(@RequestParam("page") Optional<Integer> page,
                                                          @RequestParam("keyword") Optional<String> keyword) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        Page<ChuongTrinhGiamGiaSP> giamGiaSPPage;
        if (keyword != null) {
            giamGiaSPPage = (Page<ChuongTrinhGiamGiaSP>) chuongTrinhGiamGiaSPRepository.searchMaOrTenOrTrangThai("%" + keyword.get() + "%", pageable);
        } else {
            giamGiaSPPage = chuongTrinhGiamGiaSPRepository.findAll(pageable);
        }

        return new PageDTO<>(giamGiaSPPage);

    }

//    @GetMapping("/search-trangthai")
//    public PageDTO<ChuongTrinhGiamGiaSP> searchByTrangThai(@RequestParam("page") Optional<Integer> page,
//                                                           @RequestParam("searchTrangThai") Optional<Integer> trangThai) {
//        Pageable pageable = PageRequest.of(page.orElse(0), 5);
//        Page<ChuongTrinhGiamGiaSP> giamGiaSPPage;
//        if (trangThai != null) {
//            giamGiaSPPage = (Page<ChuongTrinhGiamGiaSP>) chuongTrinhGiamGiaSPRepository.searchByTrangThai("%" +trangThai.get() + "%", pageable);
//        } else {
//            giamGiaSPPage = chuongTrinhGiamGiaSPRepository.findAll(pageable);
//        }
//
//        return new PageDTO<>(giamGiaSPPage);
//
//    }

    @PostMapping()        // http://localhost:2020/rest/chuong-trinh-giam-gia-sp/hien-thi
    public ResponseEntity<?> createVoucher(@RequestBody ChuongTrinhGiamGiaSP chuongTrinhGiamGiaSP) {
        return ResponseEntity.ok(chuongTrinhGiamGiaSPRepository.save(chuongTrinhGiamGiaSP));
    }

    @PutMapping("/update/{id}")        // http://localhost:2020/rest/chuong-trinh-giam-gia-sp/update
    public ResponseEntity<?> updateVoucher(@RequestBody ChuongTrinhGiamGiaSP km, @PathVariable UUID id) {
        Optional<ChuongTrinhGiamGiaSP> op = chuongTrinhGiamGiaSPRepository.findById(id);
        op.map(o -> {
            o.setNgayBatDau(km.getNgayBatDau());
            o.setNgayKetThuc(km.getNgayKetThuc());
            o.setTrangThai(km.getTrangThai());
            o.setPhanTramGiam(km.getPhanTramGiam());
            o.setMaKhuyenMai(km.getMaKhuyenMai());
            o.setTenKhuyenMai(km.getTenKhuyenMai());
            return chuongTrinhGiamGiaSPRepository.save(o);
        }).orElse(null);
        return ResponseEntity.ok(op);
    }

    @GetMapping("/detail/{id}")        // http://localhost:2020/rest/chuong-trinh-giam-gia-sp/detail
    public ResponseEntity<?> detail(@PathVariable UUID id) {
        return ResponseEntity.ok(chuongTrinhGiamGiaSPRepository.findById(id));
    }

    @GetMapping("/{ma}")        // http://localhost:2020/rest/chuong-trinh-giam-gia-sp/detail
    public ResponseEntity<?> getOneByMa(@PathVariable String ma) {
        return ResponseEntity.ok(chuongTrinhGiamGiaSPRepository.findChuongTrinhGiamGiaSPByMaKhuyenMai(ma));
    }

    @GetMapping("/{idKM}")        // http://localhost:2020/rest/chuong-trinh-giam-gia-sp/detail
    public ResponseEntity<?> getOneById(@PathVariable UUID idKM) {
        return ResponseEntity.ok(chuongTrinhGiamGiaSPRepository.findChuongTrinhGiamGiaSPByIdKhuyenMai(idKM));
    }

    @PutMapping("/{ma}")
    public ResponseEntity<?> update(@RequestBody ChuongTrinhGiamGiaSP ctggsp, @PathVariable("ma") String ma) {
        ChuongTrinhGiamGiaSP getOneCTGG = chuongTrinhGiamGiaSPRepository.findByMa(ma);

        getOneCTGG.setTenKhuyenMai(ctggsp.getTenKhuyenMai());
        getOneCTGG.setNgayBatDau(ctggsp.getNgayBatDau());
        getOneCTGG.setNgayKetThuc(ctggsp.getNgayKetThuc());
        getOneCTGG.setPhanTramGiam(ctggsp.getPhanTramGiam());
        getOneCTGG.setTrangThai(ctggsp.getTrangThai());

        return ResponseEntity.ok(chuongTrinhGiamGiaSPRepository.save(getOneCTGG));
    }

}
