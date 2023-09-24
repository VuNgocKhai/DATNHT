package com.example.demo.repository;

import com.example.demo.entity.ChuongTrinhGiamGiaSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface CTrinhKMSPDTO extends JpaRepository<ChuongTrinhGiamGiaSP, UUID> {

//    @Modifying
//    @Query(value = "update chuong_tring_giam_gia_san_pham set trangthai =0\n" +
//            "where  ngay_ket_thuc < getdate()", nativeQuery = true)
//
//    ChuongTrinhGiamGiaSP(Date)

}
