package com.example.demo.controller;

import com.example.demo.entity.ChuongTrinhGiamGiaSP;
import com.example.demo.repository.CTrinhKMSPDTO;
import com.example.demo.repository.ChuongTrinhGiamGiaChiTietSPRepo;
import com.example.demo.repository.ChuongTrinhGiamGiaChitietSanPhamDTO;
import com.example.demo.repository.ChuongTrinhGiamGiaSPRepo;
import com.example.demo.repository.GiayDTO;
import com.example.demo.repository.GiayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ScheduleKMSP {

    @Autowired
    ChuongTrinhGiamGiaSPRepo repo;

//    @Autowired
//    CTrinhKMSPDTO voucherDTO;
//
//    List<ChuongTrinhGiamGiaSP> listKM = voucherDTO.findAll();
//
//    @Scheduled(fixedRate = 10000)        //Điền thời gian mong muốn. Tính theo mili giây
//
//    public void datLichDayDiHoc() {
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//    }
}
