package com.example.demo.repository;

import com.example.demo.entity.DanhGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Repository
public interface DanhGiaDAO extends JpaRepository<DanhGia, UUID> {

    @Query("select dg from DanhGia dg where dg.trangThai=0")
    Page<DanhGia> findDanhGiasByTrangThai0(Pageable pageable);

    @Query("select dg from DanhGia dg where dg.giay.ma=?1 and dg.trangThai=1")
    Page<DanhGia> findDanhGiasByMaSpAndTt(String masp,Pageable pageable);

    @Query("update DanhGia set trangThai=1")
    @Modifying
    @Transactional
    void duyetAll();

    @Query("update DanhGia set trangThai=1 where id=?1")
    @Modifying
    @Transactional
    void duyetOne(UUID id);

    @Query("select count(dg) from DanhGia dg where dg.giay.ma=?1 and dg.trangThai=1")
    Integer countGiayByMaGiayAndTt(String ma);

    @Query("select avg(dg.sao) from DanhGia dg where dg.trangThai=1 and dg.giay.ma=?1 ")
    Double tbs(String mg);

    default Double customRound(String mg) {
        // You can customize this rounding function based on your requirements
        // If the decimal part is less than 0.25, round down using Math.floor
        // If the decimal part is between 0.25 and 0.75, round to the nearest 0.5
        // If the decimal part is greater than 0.75, round up using Math.ceil
        Double value=tbs(mg)==null?0:tbs(mg);
        double decimalPart = value - Math.floor(value);

        if (decimalPart < 0.25) {
            return Math.floor(value);
        } else if (decimalPart < 0.75) {
            return Math.floor(value) + 0.5;
        } else {
            return Math.ceil(value);
        }
    }
}
