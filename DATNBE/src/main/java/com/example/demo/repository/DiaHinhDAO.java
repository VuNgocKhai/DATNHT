package com.example.demo.repository;

import com.example.demo.entity.DanhMuc;
import com.example.demo.entity.DiaHinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface DiaHinhDAO extends JpaRepository<DiaHinh, UUID> {
    @Query("select p from DiaHinh p where p.ma=?1")
    DiaHinh findDiaHinhByMa(String ma);

    @Modifying
    @Transactional
    @Query("delete from DanhMuc where ma=?1")
    void deleteDiaHinhByMa(String ma);
}
