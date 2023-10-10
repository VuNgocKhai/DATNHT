package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(name = "giay")
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Giay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String ma;
    private String ten;
    @ManyToOne
    @JoinColumn(name = "id_cam_giac")
    private CamGiac cam_giac;
    @ManyToOne
    @JoinColumn(name = "id_chat_lieu")
    private ChatLieu chat_lieu;
    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc danh_muc;
    @ManyToOne
    @JoinColumn(name = "id_de_giay")
    private DeGiay de_giay;
    @ManyToOne
    @JoinColumn(name = "id_dia_hinh")
    private DiaHinh dia_hinh;
    @ManyToOne
    @JoinColumn(name = "id_do_cao_giay")
    private DoCaoGiay do_cao_giay;
    @ManyToOne
    @JoinColumn(name = "id_gioi_tinh")
    private GioiTinh gioi_tinh;
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac mau_sac;
    @ManyToOne
    @JoinColumn(name = "id_thoi_tiet_thich_hop")
    private ThoiTietThichHop thoi_tiet_thich_hop;
    @ManyToOne
    @JoinColumn(name = "id_thuong_hieu")
    private ThuongHieu thuong_hieu;
    @JsonIgnore
    @OneToMany(mappedBy = "giay",fetch = FetchType.EAGER) // lấy hết các thông tin
    Set<Anh> anhs;
    @JsonIgnore
    @OneToMany(mappedBy = "giay",fetch = FetchType.EAGER)
    Set<GiayChiTiet> giayChiTiets;
    private String mota;
    private BigDecimal gianhap;
    private BigDecimal giaban;
    private Integer trangthai;
    public String getAnhDau(Set<Anh> anhs1){
        List<Anh> list = new ArrayList<Anh>(anhs1);
        return list.get(0).getTen_url();
    };
}
