package com.example.demo.repository;

import com.example.demo.entity.GiayChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GiayChiTietDAO extends JpaRepository<GiayChiTiet, UUID> {
    @Query("select p from GiayChiTiet p where p.giay.ma=?1")
    List<GiayChiTiet> getAllByMaGiay(String ma);

    @Query("select p from GiayChiTiet p where p.giay.ma=?1 and p.kich_co.ma=?2")
    GiayChiTiet getAllByMaGiayAndSize(String ma,String size);

    @Query("select p from GiayChiTiet p where p.giay.ten like ?1 and p.kich_co.ten like ?2 and p.so_luong_ton > 0")
    Page<GiayChiTiet> getSearchsanpham(String keyword, String sỉze, Pageable pageable);

    @Query("SELECT h FROM GiayChiTiet h")
    Page<GiayChiTiet> findAllWithPagination(Pageable pageable);
}
