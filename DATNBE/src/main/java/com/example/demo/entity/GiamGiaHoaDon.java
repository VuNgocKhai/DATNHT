package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "chuong_trinh_giam_gia_hoa_don")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GiamGiaHoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Không được để trống mã !")
    private String ma;

    @NotBlank(message = "Không được để trống tên !")
    private String ten;

    @NotNull(message = "Không được để trống số tiền điều kiện !")
    private BigDecimal dieu_kien;

    @NotNull(message = "Không được để trống số tiền giảm !")
    private BigDecimal so_tien_giam;

    @NotNull(message = "Không được để trống ngày bắt đầu !")
    private Date ngay_bat_dau;

    @NotNull(message = "Không được để trống ngày kết thúc !")
    private Date ngay_ket_thuc;

    @NotNull(message = "Không được để trống số lượng !")
    private Integer so_luong;

    @NotNull(message = "Không được để trống trạng thái !")
    private Integer trangthai;

    @OneToMany(mappedBy = "gghd", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<GiamGiaChiTietHoaDon> list1;
}
