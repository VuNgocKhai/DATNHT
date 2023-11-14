package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chuong_tring_giam_gia_san_pham")
public class ChuongTrinhGiamGiaSP implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID idKhuyenMai;

    @Column(name = "ma")
    private String maKhuyenMai;

    @Column(name = "ten")
    @NotBlank(message = "Không được để trống!")
    private String tenKhuyenMai;

    @Column(name = "phan_tram_giam")
    @NotNull(message = "Không được để trống!")
    @Min(value = 0, message = "Phần trăm giảm không được âm!")
    @Max(value = 100, message = "Phần trăm giảm phải bé hơn 100!")
    private Integer phanTramGiam;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Không được để trống!")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "ngay_bat_dau")

    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Không được để trống!")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;

    @Column(name = "trangthai")
    private Integer trangThai;
}
