package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "giay_chi_tiet")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GiayChiTiet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_kich_co")
    private KichCo kich_co;
    @ManyToOne
    @JoinColumn(name = "id_giay")
    private Giay giay;
    private Integer so_luong_ton;
    private Integer trangthai;
}
