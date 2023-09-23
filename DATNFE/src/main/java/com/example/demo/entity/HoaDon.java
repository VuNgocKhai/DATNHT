package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "hoa_don")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "ngay_thanh_toan")
    private String ngayThanhToan;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

//    @ManyToOne
//    @JoinColumn(name = "id_khach_hang")
//    private KhachHang khachHang;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "trangthai")
    private Integer trangThai;
}
