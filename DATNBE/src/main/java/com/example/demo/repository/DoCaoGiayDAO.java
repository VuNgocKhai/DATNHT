package com.example.demo.repository;

import com.example.demo.entity.DanhMuc;
import com.example.demo.entity.DoCaoGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface DoCaoGiayDAO extends JpaRepository<DoCaoGiay, UUID> {
    @Query("select p from DoCaoGiay p where p.ma=?1")
    DoCaoGiay findDoCaoGiayByMa(String ma);

    @Modifying
    @Transactional
    @Query("delete from DoCaoGiay where ma=?1")
    void deleteDoCaoGiayByMa(String ma);
}
