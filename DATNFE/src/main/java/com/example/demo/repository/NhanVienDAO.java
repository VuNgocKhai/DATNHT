package com.example.demo.repository;

import com.example.demo.entity.NhanVien;
import com.example.demo.dto.NhanVienDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NhanVienDAO extends JpaRepository<NhanVien, UUID> {

    @Query(value = "select nv from NhanVien nv where nv.ma=?1")
    NhanVien findNvByMaNv(String maNv);

    @Query(value = "SELECT \n" +
            "            (SELECT COUNT(*) FROM nhan_vien WHERE trangthai=1) AS nvHienTai,\n" +
            "                (SELECT COUNT(*) FROM nhan_vien WHERE MONTH(ngay_vao_lam) = MONTH(GETDATE()) AND YEAR(ngay_vao_lam) = YEAR(GETDATE())) AS nvMoiTrongThang,\n" +
            "                (SELECT COUNT(*) FROM nhan_vien WHERE MONTH(ngay_nghi_viec) = MONTH(GETDATE()) AND YEAR(ngay_nghi_viec) = YEAR(GETDATE())) AS nvNghiTrongThang",
            nativeQuery = true)
    NhanVienDto getNhanVienDto();

    @Query(value = "select nv from NhanVien nv where " +
            " (nv.ma is null or nv.ma=:ma) and " +
            " ((nv.sdt is null or nv.sdt like %:data%) or " +
            " (nv.email is null or nv.email like %:data%) or" +
            " (nv.hoTen is null or nv.hoTen like %:data%)) and " +
            " (nv.chucVu.id is null or nv.chucVu.id=:idCv)")
    Page<NhanVien> findNhanVien(String ma,String data, UUID idCv,
                                Pageable pageable);


    Page<NhanVien> getAllByTrangThai(Integer tt,
                                Pageable pageable);
}
