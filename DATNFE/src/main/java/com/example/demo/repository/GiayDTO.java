package com.example.demo.repository;

import com.example.demo.entity.Giay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GiayDTO extends JpaRepository<Giay, UUID> {

    @Query(value = "select * from giay where giay.id not in (select giay.id from giay \n" +
            "join chuong_trinh_giam_gia_chi_tiet_san_pham \n" +
            "on giay.id = chuong_trinh_giam_gia_chi_tiet_san_pham.id_giay \n" +
            "where chuong_trinh_giam_gia_chi_tiet_san_pham.trangthai=1 )", nativeQuery = true)
    List<Giay> listGiayChuaApDung();
}
