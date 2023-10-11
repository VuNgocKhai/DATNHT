package com.example.demo.entity;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
=======
import lombok.*;
import javax.persistence.*;

import javax.persistence.Entity;
>>>>>>> origin/giamgiasanpham
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
<<<<<<< HEAD

    @ManyToOne
    @JoinColumn(name = "id_kich_co")
    private KichCo kich_co;

    @ManyToOne
    @JoinColumn(name = "id_giay")
    private Giay giay;

    private Integer so_luong_ton;

=======
    @ManyToOne
    @JoinColumn(name = "id_kich_co")
    private KichCo kich_co;
    @ManyToOne
    @JoinColumn(name = "id_giay")
    private Giay giay;
    private Integer so_luong_ton;
>>>>>>> origin/giamgiasanpham
    private Integer trangthai;
}
