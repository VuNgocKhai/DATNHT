package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name = "gio_hang")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String ma;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khach_hang;
    private Date ngay_tao;
    private Date ngay_cap_nhap;
    private String ghi_chu;
    private Integer trangthai;
}
