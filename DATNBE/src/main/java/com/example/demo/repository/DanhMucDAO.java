package com.example.demo.repository;

import com.example.demo.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface DanhMucDAO extends JpaRepository<DanhMuc, UUID> {
    @Query("select p from DanhMuc p where p.ma=?1")
    DanhMuc findDanhMucByMa(String ma);

    @Modifying
    @Transactional
    @Query("delete from DanhMuc where ma=?1")
    void deleteDanhMucByMa(String ma);
}
