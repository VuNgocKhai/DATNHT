package com.example.demo.repository;

import com.example.demo.entity.CamGiac;
import com.example.demo.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface CamGiacDAO extends JpaRepository<CamGiac, UUID> {
    @Query("select p from CamGiac p where p.ma=?1")
    CamGiac findCamGiacByMa(String ma);

    @Modifying
    @Transactional
    @Query("delete from CamGiac where ma=?1")
    void deleteCamGiacByMa(String ma);
}
