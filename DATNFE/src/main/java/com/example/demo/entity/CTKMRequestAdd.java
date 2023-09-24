package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="chuong_trinh_giam_gia_chi_tiet_san_pham")
public class CTKMRequestAdd implements Serializable {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO)
    private UUID id;

    @Column(name = "id_giay")
    private UUID idGiay;

    @Column(name = "id_chuong_trinh_giam_gia")
    private UUID idKhuyenMai;

    @Column(name = "so_tien_da_giam")
    private BigDecimal soTienDaGiam;
//
//    private BigDecimal giaBan;
//
//    private Integer phanTramGiam;

    @Column(name = "trangthai")
    private Integer trangThai;

//    public ChuongTrinhGiamGiaChiTietSP dtoCTKM(ChuongTrinhGiamGiaChiTietSP ctkm){
//        ctkm.setGiay(Giay.builder().idGiay(this.getIdGiay()).build());
//        ctkm.setChuongTrinhGiamGiaSP(ChuongTrinhGiamGiaSP.builder().idKhuyenMai(this.getIdKhuyenMai()).build());
//        ctkm.setTrangThai(1);
//        ctkm.setSoTienDaGiam(this.getSoTienDaGiam());
//        return ctkm;
//    }
}
